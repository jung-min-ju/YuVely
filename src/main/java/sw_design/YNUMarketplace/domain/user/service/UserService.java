package sw_design.YNUMarketplace.domain.user.service;

import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignInDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignUpDto;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AllJwtTokenDto;

public interface UserService {
    void checkExits(SignUpDto signUpDto);
    void saveUser(SignUpDto signUpDto);
    AllJwtTokenDto signIn(SignInDto signInDto);
}
