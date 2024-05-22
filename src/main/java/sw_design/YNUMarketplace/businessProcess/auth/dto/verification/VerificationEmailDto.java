package sw_design.YNUMarketplace.businessProcess.auth.dto.verification;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerificationEmailDto {

    @NotBlank(message = "이메일은 필수 값입니다.")
    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @Size(min = 5, max = 254, message = "5이상, 254이하 이메일을 입력해주세요.")
    @Pattern(regexp = "^[\\w.%+-]+@yu\\.ac\\.kr$", message = "영남대학교 전용 이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이메일 인증번호를 입력해주세요.")
    private String emailVerificationCode;

}
