package sw_design.YNUMarketplace.springSecurity.redis.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 60) //60일 뒤 자동삭제
public class RedisRefreshToken {

    @Id
    private String id; //여기에 해당 리프래시 토큰 가진 managerEmailId 들어감
    private String refrehToken;

}
