package sw_design.YNUMarketplace.exception.collections.business;

import sw_design.YNUMarketplace.exception.message.SmsExceptionMessage;

public class FailedSendSmsToClient extends BusinessException{
    public FailedSendSmsToClient() {
        super(SmsExceptionMessage.FailedSendSmsToClient);
    }
}