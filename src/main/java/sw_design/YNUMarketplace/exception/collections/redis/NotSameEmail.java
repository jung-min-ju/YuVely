package sw_design.YNUMarketplace.exception.collections.redis;

import sw_design.YNUMarketplace.exception.collections.business.BusinessException;
import sw_design.YNUMarketplace.exception.message.RedisExceptionMessage;

public class NotSameEmail extends BusinessException {
    public NotSameEmail() {
        super(RedisExceptionMessage.NotSameEmail);
    }
}