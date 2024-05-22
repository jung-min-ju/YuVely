package sw_design.YNUMarketplace.exception.message;

public class AuthExceptionMessage {
    public static final String UserAlreadyExists = "이미 존재하는 유저입니다.";
    public static final String UnAuthenticatedUserAccess=  "인증되지 않은 사용자입니다.";
    public static final String UsernameNotFoundException = "입력하신 이메일의 계정이 존재하지 않습니다.";
    public static final String ACCOUNT_PASSWORD_NOT_MATCH = "입력하신 Password가 일치하지 않습니다.";
    public static final String DuplicatedUniqueKey = "이미 등록된 정보입니다. 다른 정보를 입력해주세요.";
    public static final String UnVerifiedUserInfo = "인증을 먼저 진행해주세요.";
}
