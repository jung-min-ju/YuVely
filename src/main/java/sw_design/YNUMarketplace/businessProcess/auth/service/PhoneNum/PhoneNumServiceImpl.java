package sw_design.YNUMarketplace.businessProcess.auth.service.PhoneNum;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoBadRequestException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.stereotype.Service;
import sw_design.YNUMarketplace.businessProcess.auth.dto.smtp.PhoneNumDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.verification.VerificationPhoneNumDto;
import sw_design.YNUMarketplace.businessProcess.util.UtilService;
import sw_design.YNUMarketplace.config.smtp.PhoneConfig;
import sw_design.YNUMarketplace.exception.collections.business.FailedSendSmsToClient;
import sw_design.YNUMarketplace.exception.collections.business.UnVerifiedUserException;
import sw_design.YNUMarketplace.exception.collections.redis.NotMatchVerificatonCodeByPhoneNum;
import sw_design.YNUMarketplace.exception.collections.redis.NotSamePhoneNum;
import sw_design.YNUMarketplace.springSecurity.redis.model.auth.RedisAuthCode;
import sw_design.YNUMarketplace.springSecurity.redis.service.auth.AuthRedisService;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneNumServiceImpl implements PhoneNumService{
    private final PhoneConfig phoneConfig;
    private final AuthRedisService authRedisService;
    private final UtilService utilService;
    private DefaultMessageService messageService;

    @PostConstruct //의존성 주입 끝난 후 자동호출
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(
                phoneConfig.getApiKey(), phoneConfig.getSecretKey(), "https://api.coolsms.co.kr");
    }

    private void saveRedis(String phoneNum,String randomNum){
        RedisAuthCode authCodeDto = RedisAuthCode.builder()
                .id(phoneNum)
                .code(randomNum)
                .build();
        authRedisService.save(authCodeDto);
    }

    @Override
    public void sendVerifyNumberByPhoneNum(PhoneNumDto phoneNumDto)  {
        //랜덤값 만들기
        String randomNum = utilService.getRandomNum();

        //1. sendOne()-> coolSMS 외부 api에 내용 담아 요청하는 함수
        Message message = new Message();
        message.setFrom(phoneConfig.getAdminphone());
        message.setTo(phoneNumDto.getPhoneNum());
        message.setText("YuVely 인증번호 : "+randomNum);

        //2. SingleMessageSendingRequest -> 외부 api 요청 클래스
        //3. SingleMessageSentResponse -> 외부 api 응답 클래스
        SingleMessageSentResponse response;
        try {
            response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            // 성공적으로 응답을 받았을 때의 처리 로직
        } catch (Throwable throwable) {
            if (throwable instanceof NurigoBadRequestException) {
                // ValidationError 또는 FailedToAddMessage 에러 발생 시의 처리 로직
                throw new FailedSendSmsToClient();
            } else {
                // 그 외에 대한 에러 처리 로직
               throw new RuntimeException();
            }
        }

        //redis 서버에 저장
        saveRedis(phoneNumDto.getPhoneNum(), randomNum);

        //임시로 확인 돌리기
        //checkVerifyNumberByPhoneNum(phoneNumDto.getPhoneNum(), randomNum);
    }

    @Override
    public void checkVerifyNumberByPhoneNum(VerificationPhoneNumDto verificationPhoneNumDto) {

        Optional<RedisAuthCode> authCodeDto = authRedisService.find(verificationPhoneNumDto.getPhoneNum());

        //인증번호 전송을 안한 경우
        if(authCodeDto.isEmpty()) throw new UnVerifiedUserException();

        //전화번호 인증 신청할때랑, 최종 제출한 전화번호가 다른 경우
        if(!authCodeDto.isPresent()) throw new NotSamePhoneNum();

        //인증 코드가 다른 경우 에러 필요
        if(!verificationPhoneNumDto.getPhoneVerificationCode().equals(authCodeDto.get().getCode())) throw new NotMatchVerificatonCodeByPhoneNum();

    }

}
