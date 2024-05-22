package sw_design.YNUMarketplace.exception.collections.redis;


import sw_design.YNUMarketplace.exception.collections.business.BusinessException;
import sw_design.YNUMarketplace.exception.message.RedisExceptionMessage;

public class NotMatchVerificatonCodeByEmail extends BusinessException {
    public NotMatchVerificatonCodeByEmail() {
        super(RedisExceptionMessage.NotMatchVerificatonCodeByEmail);
    }
}
