package co.simplon.socworkbusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config {
    @Value("${co.simplon.socwork.cors}")
    private String origins;

    @Value("${co.simplon.socwork.cost}")
    private Integer cost;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(cost);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PATCH", "PUT").allowedOrigins(origins);
            }
        };
    }
}
