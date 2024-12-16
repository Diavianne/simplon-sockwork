package co.simplon.socworkbusiness.config;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
@EnableWebSecurity
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

    @Bean
    JwtDecoder jwtDecoder() { // Tell Spring how to verify JWT signature
	SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HMACSHA256");
	NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
	return decoder;
    }

    @Bean // Change default behaviour
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	// Default Spring behaviour: PoLP (no authorization at all)
	// Relies on JWT
	// authorize some requests or not
	// ???
// V1: my version
//	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
//		.authorizeHttpRequests(authorize -> authorize.requestMatchers("/accounts", "/accounts/authenticate")
//			.permitAll().anyRequest().authenticated())
//		.oauth2ResourceServer((srv) -> srv.jwt(Customizer.withDefaults())).build();

//	V2 : Correction by Frank
	return http.cors(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
		// Multiple matchers to map verbs + paths + authorizations
		// "authorizations": anonymous, permit, deny and more...
		// By configuration (filterChain), also by annotations...
		.authorizeHttpRequests((req) -> req
			.requestMatchers(HttpMethod.POST, "/accounts", "/accounts/authenticate").anonymous())
		// Always last rule:
		.authorizeHttpRequests((reqs) -> reqs.anyRequest().authenticated())
		.oauth2ResourceServer((srv) -> srv.jwt(Customizer.withDefaults()))
		// The build method builds the configured SecurityFilterChain
		// with all the specified configuration
		.build();
    }

}
