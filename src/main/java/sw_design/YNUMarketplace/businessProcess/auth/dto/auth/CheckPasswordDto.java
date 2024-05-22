package sw_design.YNUMarketplace.businessProcess.auth.dto.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckPasswordDto {
    String passwrod;
    String checkPassword;
}
