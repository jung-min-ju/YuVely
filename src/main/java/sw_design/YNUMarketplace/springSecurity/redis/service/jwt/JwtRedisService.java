package sw_design.YNUMarketplace.springSecurity.redis.service.jwt;

import sw_design.YNUMarketplace.springSecurity.redis.model.jwt.RedisRefreshToken;

import java.util.Optional;

public interface JwtRedisService {
    RedisRefreshToken save(RedisRefreshToken redisRefreshTokenDto);
    //리프레시 토큰을 redis 에 저장
    Optional<RedisRefreshToken> find(String id);

}