package sw_design.YNUMarketplace.config.collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/api/auth/**")
                .allowedOrigins("*")
                .allowedMethods("POST");

        registry
                .addMapping("/api/verify/**")
                .allowedOrigins("*")
                .allowedMethods("POST");
        registry
                .addMapping("/swagger-ui.html")
                .allowedOrigins("*")
                .allowedMethods("POST");
    }

}