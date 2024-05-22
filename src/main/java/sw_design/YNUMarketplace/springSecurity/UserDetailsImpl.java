package sw_design.YNUMarketplace.springSecurity;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sw_design.YNUMarketplace.domain.user.model.User;

import java.util.Collection;
import java.util.Collections;

@Getter
public class UserDetailsImpl implements UserDetails {
    private final User user;
    private final Collection<?extends GrantedAuthority> authorities;

    public UserDetailsImpl(User user, User.UserRoleEnum role) {
        this.user = user;
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() { //여기에 비밀번호를 저장하는 건 위험
        return null;
    }

    @Override
    public String getUsername() {
        return user.getEmailId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
