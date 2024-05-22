package sw_design.YNUMarketplace.exception.collections.redis;


import sw_design.YNUMarketplace.exception.collections.business.BusinessException;
import sw_design.YNUMarketplace.exception.message.RedisExceptionMessage;

public class NotMatchVerificatonCodeByPhoneNum extends BusinessException {
    public NotMatchVerificatonCodeByPhoneNum() {
        super(RedisExceptionMessage.NotMatchVerificatonCodeByPhoneNum);
    }
}
