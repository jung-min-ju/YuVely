package sw_design.YNUMarketplace.exception.collections.business;

import sw_design.YNUMarketplace.exception.message.AuthExceptionMessage;

public class UserAlreadyException extends BusinessException{
    public UserAlreadyException() {
        super(AuthExceptionMessage.UserAlreadyExists);
    }
}
