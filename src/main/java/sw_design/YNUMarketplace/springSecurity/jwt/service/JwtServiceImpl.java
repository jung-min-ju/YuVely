package sw_design.YNUMarketplace.springSecurity.jwt.service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw_design.YNUMarketplace.exception.collections.business.TokenMissingException;
import sw_design.YNUMarketplace.exception.collections.redis.RefreshTokenExpirationException;
import sw_design.YNUMarketplace.springSecurity.jwt.JwtTokenProvider;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AccessTokenDto;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.TokenSubAndRoleDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements JwtService{
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AccessTokenDto updateAccessToken(HttpServletRequest request) {
        //리프래시 토큰이 있는지 검증
        String refreshToken = jwtTokenProvider.resolveToken(request);
        if(refreshToken.equals(null)) throw new TokenMissingException();

        boolean isRefreshTokenValid = jwtTokenProvider.validateRefreshTokenInRedis(refreshToken);

        //해당 토큰이 레디스에 존재하는지 + 토큰 자체의 유효성 검증
        if (!isRefreshTokenValid) throw new RefreshTokenExpirationException();

        TokenSubAndRoleDto tokenSubAndRoleDto = jwtTokenProvider.getSubjectAndRoleFromRefresh(refreshToken);
        return jwtTokenProvider.createAccessToken(tokenSubAndRoleDto.getAccountId(), tokenSubAndRoleDto.getRole());
    }

}