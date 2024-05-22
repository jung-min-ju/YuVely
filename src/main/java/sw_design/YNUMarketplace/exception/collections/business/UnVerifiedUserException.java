package sw_design.YNUMarketplace.exception.collections.business;


import sw_design.YNUMarketplace.exception.message.AuthExceptionMessage;

public class UnVerifiedUserException extends BusinessException{
    public UnVerifiedUserException() {
        super(AuthExceptionMessage.UnVerifiedUserInfo);
    }
}
