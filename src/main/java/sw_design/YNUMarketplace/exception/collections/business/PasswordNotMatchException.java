package sw_design.YNUMarketplace.exception.collections.business;

import sw_design.YNUMarketplace.exception.message.AuthExceptionMessage;

public class PasswordNotMatchException extends BusinessException {
    public PasswordNotMatchException() {
        super(AuthExceptionMessage.ACCOUNT_PASSWORD_NOT_MATCH);
    }
}