package sw_design.YNUMarketplace.springSecurity.jwt;


import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sw_design.YNUMarketplace.config.collections.JwtConfig;
import sw_design.YNUMarketplace.config.contant.JwtConstants;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AccessTokenDto;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.RefreshTokenDto;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.TokenSubAndRoleDto;
import sw_design.YNUMarketplace.springSecurity.redis.model.jwt.RedisRefreshToken;
import sw_design.YNUMarketplace.springSecurity.redis.service.jwt.JwtRedisService;

import javax.crypto.SecretKey;
import java.util.Date;

import static sw_design.YNUMarketplace.config.contant.JwtConstants.BEARER_TYPE;

@Component
@Slf4j
public class JwtTokenProvider {
    private SecretKey secretKey;
    private Long refreshTokenExpirationMinutes;
    private Long accessTokenExpirationMinutes;
    private final JwtRedisService redisService;

    public JwtTokenProvider(JwtConfig jwtConfig, JwtRedisService redisService) {
        refreshTokenExpirationMinutes = jwtConfig.getRefreshTokenExpirationMinutes();
        accessTokenExpirationMinutes = jwtConfig.getAccessTokenExpirationMinutes();
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtConfig.getSecretKey()));
        this.redisService = redisService;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }
    //헤더에 포함되어 날아온 Authorization을 가져온다

    public String validateAdminAccessTokenData(String token) {
        // SignatureException :  비밀키 일치 X 처리
        //ExpiredJwtException :  만료 exception 처리
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        return claimsJws.getPayload().getSubject();
    }

    public AccessTokenDto createAccessToken(String managerEmailId, String roles) {
        JwtBuilder token = Jwts.builder();

        Date now = new Date();
        Date accessExpiredTime = new Date(now.getTime() + accessTokenExpirationMinutes );

        String jwt = Jwts.builder()
                .header().type(JwtConstants.JWT)
                .and()
                .claims()
                .subject(managerEmailId)
                .add("role",roles)
                .issuedAt(now)
                .expiration(accessExpiredTime)
                .and()
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

        return AccessTokenDto.builder()
                .accessToken(jwt)
                .accessTokenExpiredTime(accessExpiredTime)
                .build();

    }

    public RefreshTokenDto createRefreshToken(String managerEmailId, String roles) {
        JwtBuilder token = Jwts.builder();

        Date now = new Date();
        Date refreshExpiredTime = new Date(now.getTime() + refreshTokenExpirationMinutes );


        String jwt = Jwts.builder()
                .header().type(JwtConstants.JWT)
                .and()
                .claims()
                .subject(managerEmailId)
                .add("role",roles)
                .issuedAt(now)
                .expiration(refreshExpiredTime)
                .and()
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();

        RedisRefreshToken refreshToken = RedisRefreshToken.builder()
                .id(managerEmailId)
                .refrehToken(jwt)
                .build();

        redisService.save(refreshToken);

        return RefreshTokenDto.builder()
                .refreshToken(jwt)
                .refreshTokenExpiredTime(refreshExpiredTime)
                .build();

    }

    public boolean validateRefreshTokenInRedis(String token) {
        // SignatureException :  비밀키 일치 X 처리
        //ExpiredJwtException :  만료 exception 처리
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        //sub와 권한 가져옴
        String sub = claimsJws.getPayload().getSubject();
        if(redisService.find(sub).equals(null)) return false;

        //토큰이 만료된 거라면 false, 아니면 true 반환
        return !claimsJws.getPayload().getExpiration().before(new Date());
    }

    public TokenSubAndRoleDto getSubjectAndRoleFromRefresh (String token) {
        //토큰 기반 회원 정보 추출
        //jwt parser 을 통해 SecretKey를 설정
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token);

        //claim을 추출해서 토큰을 생성할때 넣었던 sub 값과 권한 추출.
        String sub = claimsJws.getPayload().getSubject();
        String role = (String) claimsJws.getPayload().get("role");

        TokenSubAndRoleDto tokenSubAndRoleDto = TokenSubAndRoleDto.builder()
                .accountId(sub)
                .role(role)
                .build();
        //account ID 와 roles 을 반환

        return tokenSubAndRoleDto;
    }



}

