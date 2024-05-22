package sw_design.YNUMarketplace.businessProcess.auth.service.PhoneNum;

import com.fasterxml.jackson.core.JsonProcessingException;
import sw_design.YNUMarketplace.businessProcess.auth.dto.smtp.PhoneNumDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.verification.VerificationPhoneNumDto;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public interface PhoneNumService {
    void sendVerifyNumberByPhoneNum(PhoneNumDto phoneNumDto) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException;
    void checkVerifyNumberByPhoneNum(VerificationPhoneNumDto verificationPhoneNumDto);
}
