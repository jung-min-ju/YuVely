package sw_design.YNUMarketplace.springSecurity.redis.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "AuthCodeToken", timeToLive = 60 * 60 * 24) //24시간 뒤 자동삭제
public class RedisAuthCode {
    private String id; // 이메일 또는 전화번호
    private String code; // 인증 코드
}