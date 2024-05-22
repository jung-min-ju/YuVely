package sw_design.YNUMarketplace.springSecurity.redis.service.auth;

import sw_design.YNUMarketplace.springSecurity.redis.model.auth.RedisAuthCode;

import java.util.Optional;

public interface AuthRedisService {
    RedisAuthCode save(RedisAuthCode authCode);
    //이메일/전화번호 인증토큰을 redis 에 저장

    Optional<RedisAuthCode> find(String id);
}
