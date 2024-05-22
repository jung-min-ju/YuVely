package sw_design.YNUMarketplace.exception.collections.business;

import sw_design.YNUMarketplace.exception.message.AuthExceptionMessage;

public class UsernameNotFoundException extends BusinessException {
    public UsernameNotFoundException() {
        super(AuthExceptionMessage.UsernameNotFoundException);
    }
}
