package sw_design.YNUMarketplace.springSecurity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sw_design.YNUMarketplace.domain.user.model.User;
import sw_design.YNUMarketplace.domain.user.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String managerEmail) throws UsernameNotFoundException {
        final User user = userRepository.findByEmailId(managerEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Manager Not Found"));
        return new UserDetailsImpl(user, user.getRole());
    }

}


/*
 * 이 클래스는 사용자 인증을 위한 사용자 세부 정보를 로드하는 역할을 합니
 * 사용자의 이메일을 통해 데이터베이스에서 사용자를 조회하고, 조회된 사용자 정보를 Spring Security가 이해할 수 있는 UserDetails 형태로 변환하여 반환합니다.
 * 만약 사용자가 존재하지 않으면 예외를 던집니다. 이 과정은 Spring Security가 사용자 인증을 수행할 때 사용됩니다.
 * */