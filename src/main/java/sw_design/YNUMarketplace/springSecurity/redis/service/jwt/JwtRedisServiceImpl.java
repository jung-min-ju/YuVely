package sw_design.YNUMarketplace.springSecurity.redis.service.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sw_design.YNUMarketplace.springSecurity.redis.model.jwt.RedisRefreshToken;
import sw_design.YNUMarketplace.springSecurity.redis.repository.jwt.RefreshTokenRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtRedisServiceImpl implements JwtRedisService{
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    public RedisRefreshToken save(RedisRefreshToken redisRefreshTokenDto) {
        return refreshTokenRepository.save(redisRefreshTokenDto);
    }

    @Override
    public Optional<RedisRefreshToken> find(String id) {
        return refreshTokenRepository.findById(id);
    }
}
