package sw_design.YNUMarketplace.exception.collections.business;


import sw_design.YNUMarketplace.exception.message.AuthExceptionMessage;

public class DuplicateUniqueKeyException extends BusinessException{
    public DuplicateUniqueKeyException() {
        super(AuthExceptionMessage.DuplicatedUniqueKey);
    }
}
