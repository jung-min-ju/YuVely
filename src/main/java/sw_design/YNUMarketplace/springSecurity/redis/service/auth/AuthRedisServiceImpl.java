package sw_design.YNUMarketplace.springSecurity.redis.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sw_design.YNUMarketplace.springSecurity.redis.model.auth.RedisAuthCode;
import sw_design.YNUMarketplace.springSecurity.redis.repository.auth.AuthenticationCode;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthRedisServiceImpl implements AuthRedisService {
    private final AuthenticationCode authenticationCode;
    @Override
    public RedisAuthCode save(RedisAuthCode authCodeDto) {
       return authenticationCode.save(authCodeDto);
    }

    @Override
    public Optional<RedisAuthCode> find(String id) {
        return authenticationCode.findById(id);
    }

}

