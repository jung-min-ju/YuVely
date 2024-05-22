package sw_design.YNUMarketplace.businessProcess.auth.service.Email;

import jakarta.mail.MessagingException;
import sw_design.YNUMarketplace.businessProcess.auth.dto.smtp.EmailDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.verification.VerificationEmailDto;

public interface EmailService {
    void sendVerifyNumberByEmail(EmailDto emailDto) throws MessagingException;
    void checkVerifyNumberByEmail(VerificationEmailDto verificationEmailDto);
}
