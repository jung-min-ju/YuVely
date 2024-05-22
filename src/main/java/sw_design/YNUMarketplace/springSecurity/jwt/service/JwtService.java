package sw_design.YNUMarketplace.springSecurity.jwt.service;


import jakarta.servlet.http.HttpServletRequest;
import sw_design.YNUMarketplace.springSecurity.redis.dto.AccessTokenDto;

public interface JwtService {
    AccessTokenDto updateAccessToken(HttpServletRequest request);
}
