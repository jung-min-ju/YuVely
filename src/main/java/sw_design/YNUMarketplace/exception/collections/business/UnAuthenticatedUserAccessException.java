package sw_design.YNUMarketplace.exception.collections.business;

import sw_design.YNUMarketplace.exception.message.authMessage;

public class UnAuthenticatedUserAccessException extends BusinessException{
    public UnAuthenticatedUserAccessException() {
        super(authMessage.UnAuthenticatedUserAccess);
    }
}
