package sw_design.YNUMarketplace.springSecurity.jwt.controller;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sw_design.YNUMarketplace.springSecurity.jwt.service.JwtService;
import sw_design.YNUMarketplace.springSecurity.redis.dto.AccessTokenDto;

@RestController
@AllArgsConstructor
public class JwtController {
    private final JwtService jwtService;

    @PostMapping("/update/AccessToken")
    public AccessTokenDto updateAccessToken(HttpServletRequest request){
        return jwtService.updateAccessToken(request);
    }


}
