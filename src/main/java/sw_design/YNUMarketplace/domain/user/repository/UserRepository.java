package sw_design.YNUMarketplace.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sw_design.YNUMarketplace.domain.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailId(String emailId);
    Optional<User> findByPhoneNum(String phoneNum);
}
