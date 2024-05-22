package sw_design.YNUMarketplace.businessProcess.auth.service.Email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sw_design.YNUMarketplace.businessProcess.auth.dto.smtp.EmailDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.verification.VerificationEmailDto;
import sw_design.YNUMarketplace.businessProcess.util.UtilService;
import sw_design.YNUMarketplace.exception.collections.business.UnVerifiedUserException;
import sw_design.YNUMarketplace.exception.collections.redis.NotMatchVerificatonCodeByEmail;
import sw_design.YNUMarketplace.exception.collections.redis.NotSameEmail;
import sw_design.YNUMarketplace.springSecurity.redis.model.auth.RedisAuthCode;
import sw_design.YNUMarketplace.springSecurity.redis.service.auth.AuthRedisService;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final AuthRedisService authRedisService;
    private final UtilService utilService;

    private void sendEmail(String email, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("minju9711@naver.com");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        this.javaMailSender.send(message);
    }

    private void saveRedis(String email, String randomNum) {
        RedisAuthCode redisAuthCode = RedisAuthCode.builder()
                .id(email)
                .code(randomNum)
                .build();
        authRedisService.save(redisAuthCode);
    }

    @Override
    public void sendVerifyNumberByEmail(EmailDto emailDto) throws MessagingException {
        String randomNum = utilService.getRandomNum();
        String email = emailDto.getEmail();
        sendEmail(email, "YuVely 인증",
                String.format("<p>인증번호 : <span style=\"color:red\">%s<span></p>", randomNum)
        );
        saveRedis(email, randomNum);
    }

    @Override
    public void checkVerifyNumberByEmail(VerificationEmailDto verificationEmailDto) {

        Optional<RedisAuthCode> authCodeDto = authRedisService.find(verificationEmailDto.getEmail());

        if(authCodeDto.isEmpty()) throw new UnVerifiedUserException();

        //이메일 인증 신청할때랑, 최종 제출한 이메일이 다른 경우
        if (!authCodeDto.isPresent()) throw new NotSameEmail();

        //이메일 인증 신청할때랑, 최종 제출한 이메일이 다른 경우, 즉 해당 optionall이 null인 경우 에러필요
        if (!verificationEmailDto.getEmailVerificationCode().equals(authCodeDto.get().getCode()))
            throw new NotMatchVerificatonCodeByEmail();  //번호 다른 경우 에러 필요
    }

}
