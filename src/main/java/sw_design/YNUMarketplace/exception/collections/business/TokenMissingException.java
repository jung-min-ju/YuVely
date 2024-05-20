package sw_design.YNUMarketplace.exception.collections.business;


import sw_design.YNUMarketplace.exception.message.TokenExceptonMessage;

public class TokenMissingException extends BusinessException {
    public TokenMissingException() {
        super(TokenExceptonMessage.TOKEN_MISSING_HEADER);
    }

}
