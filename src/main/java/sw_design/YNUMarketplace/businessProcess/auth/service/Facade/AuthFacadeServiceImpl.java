package sw_design.YNUMarketplace.businessProcess.auth.service.Facade;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.CheckPasswordDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignInDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignUpDto;
import sw_design.YNUMarketplace.businessProcess.util.UtilService;
import sw_design.YNUMarketplace.domain.user.service.UserService;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AllJwtTokenDto;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthFacadeServiceImpl implements AuthFacadeService {
    private final UtilService utilService;
    private final UserService userService;

    @Override
    @Transactional
    public void authSignup(SignUpDto signUpDto) throws IOException {
        //1. 비밀번호 확인
        CheckPasswordDto checkPasswordDto = CheckPasswordDto.builder()
                .passwrod(signUpDto.getPassword())
                .checkPassword(signUpDto.getPasswordCheck())
                .build();

        utilService.checkPassword(checkPasswordDto);

        //2. 각 db에 전달받은 이메일, 전화번호 값이 있는지 확인
        userService.checkExits(signUpDto);

        //3. db 저장 로직
        userService.saveUser(signUpDto);
    }

    @Override
    public AllJwtTokenDto authSignIn(SignInDto signDto) {
        return userService.signIn(signDto);
    }

}
