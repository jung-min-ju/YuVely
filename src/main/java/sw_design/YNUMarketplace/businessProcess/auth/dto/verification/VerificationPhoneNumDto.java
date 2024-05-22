package sw_design.YNUMarketplace.businessProcess.auth.dto.verification;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationPhoneNumDto {

    @NotBlank(message = "전화번호는 필수 값입니다.")
    @Pattern(regexp="^\\d{3}\\d{3,4}\\d{4}$", message="유효한 휴대폰 번호를 입력해주세요.")
    private String phoneNum;

    @NotBlank(message = "전화번호 인증번호를 입력해주세요.")
    private String phoneVerificationCode;

}
