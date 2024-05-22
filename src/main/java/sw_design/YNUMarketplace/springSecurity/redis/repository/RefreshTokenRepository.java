package sw_design.YNUMarketplace.springSecurity.redis.repository;

import org.springframework.data.repository.CrudRepository;
import sw_design.YNUMarketplace.springSecurity.redis.model.RedisRefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RedisRefreshToken, String> {

}