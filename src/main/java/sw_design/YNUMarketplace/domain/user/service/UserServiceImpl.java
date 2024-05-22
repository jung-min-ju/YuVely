package sw_design.YNUMarketplace.domain.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignInDto;
import sw_design.YNUMarketplace.businessProcess.auth.dto.auth.SignUpDto;
import sw_design.YNUMarketplace.domain.user.model.User;
import sw_design.YNUMarketplace.domain.user.repository.UserRepository;
import sw_design.YNUMarketplace.exception.collections.business.DuplicateUniqueKeyException;
import sw_design.YNUMarketplace.exception.collections.business.PasswordNotMatchException;
import sw_design.YNUMarketplace.exception.collections.business.UserAlreadyException;
import sw_design.YNUMarketplace.exception.collections.business.UsernameNotFoundException;
import sw_design.YNUMarketplace.springSecurity.jwt.JwtTokenProvider;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AccessTokenDto;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.AllJwtTokenDto;
import sw_design.YNUMarketplace.springSecurity.redis.dto.jwt.RefreshTokenDto;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void checkExits(SignUpDto signUpDto) {
        //해당 멤버가 존재하는지 확인
        Optional<User> foundEmail = this.userRepository.findByEmailId(signUpDto.getEmail());
        Optional<User> foundPhone = this.userRepository.findByPhoneNum(signUpDto.getPhoneNum());
        if(foundEmail.isPresent()||foundPhone.isPresent()) throw new UserAlreadyException();
    }

    @Override
    public void saveUser(SignUpDto signUpDto) {
        String encodePassword = passwordEncoder.encode(signUpDto.getPassword());

        User newUser = User.builder()
                .emailId(signUpDto.getEmail())
                .phoneNum(signUpDto.getPhoneNum())
                .password(encodePassword)
                .role(User.UserRoleEnum.USER)
                .productList(new ArrayList<>())
                .build();

        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) { //unique 키 중복시 발생시킬 오류
            throw new DuplicateUniqueKeyException();
        }
    }

    @Override
    public AllJwtTokenDto signIn(SignInDto signInDto) {
        Optional<User> found = userRepository.findByEmailId(signInDto.getEmail());

        //이메일 존재하지 않는 경우
        if(!found.isPresent()) throw new UsernameNotFoundException();
        //비밀번호 다른 경우
        if(!passwordEncoder.matches(signInDto.getPassword(), found.get().getPassword())){
            throw new PasswordNotMatchException();
        }

        //토큰 발급
        AccessTokenDto accessTokenDto = jwtTokenProvider.createAccessToken(found.get().getEmailId(), String.valueOf(found.get().getRole()));
        RefreshTokenDto refreshTokenDto = jwtTokenProvider.createRefreshToken(found.get().getEmailId(), String.valueOf(found.get().getRole()));

        return AllJwtTokenDto
                .builder()
                .accessTokenDto(accessTokenDto)
                .refreshTokenDto(refreshTokenDto)
                .build();
    }

}
