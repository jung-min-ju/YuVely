package sw_design.YNUMarketplace.exception.collections.business;

import sw_design.YNUMarketplace.exception.message.AuthExceptionMessage;

public class UnAuthenticatedUserAccessException extends BusinessException{
    public UnAuthenticatedUserAccessException() {
        super(AuthExceptionMessage.UnAuthenticatedUserAccess);
    }
}
