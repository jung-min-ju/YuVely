package sw_design.YNUMarketplace.exception.collections.redis;

import sw_design.YNUMarketplace.exception.collections.business.BusinessException;
import sw_design.YNUMarketplace.exception.message.RedisExceptionMessage;

public class RefreshTokenExpirationException extends BusinessException {
    public RefreshTokenExpirationException() {
        super(RedisExceptionMessage.RefreshTokenExpirationException);
    }
}
