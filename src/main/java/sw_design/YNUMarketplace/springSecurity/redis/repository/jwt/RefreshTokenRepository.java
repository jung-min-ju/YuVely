package sw_design.YNUMarketplace.springSecurity.redis.repository.jwt;

import org.springframework.data.repository.CrudRepository;
import sw_design.YNUMarketplace.springSecurity.redis.model.jwt.RedisRefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RedisRefreshToken, String> {

}