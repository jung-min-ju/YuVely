package sw_design.YNUMarketplace.springSecurity.redis.repository.auth;

import org.springframework.data.repository.CrudRepository;
import sw_design.YNUMarketplace.springSecurity.redis.model.auth.RedisAuthCode;

public interface AuthenticationCode extends CrudRepository<RedisAuthCode, String> {
}
