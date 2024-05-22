package sw_design.YNUMarketplace.exception.collections.InputValid;


import static sw_design.YNUMarketplace.exception.message.InvalidRequestExceptionMessage.BindingErrorMessage;

public class BindingErrors extends InvalidRequestException{
    public BindingErrors() {
        super(BindingErrorMessage);
    }
}
