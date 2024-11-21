package co.simplon.socworkbusiness.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
public class Config {
    @Value("${co.simplon.socwork.cors}")
    private String origins;

    @Value("${co.simplon.socwork.cost}")
    private Integer cost;

    @Value("${co.simplon.socwork.secret}")
    private String secret;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PATCH", "PUT").allowedOrigins(origins);
	    }
	};
    }

    // injection dependences
    @Bean
    public PasswordEncoder encoder() {
	return new BCryptPasswordEncoder(cost);
    }

    @Bean
    JwtProvider jwtProvider() {
	Algorithm algorithm = Algorithm.HMAC256(secret);
	return new JwtProvider(algorithm);
    }
}
