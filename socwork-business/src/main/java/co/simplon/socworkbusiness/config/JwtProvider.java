package co.simplon.socworkbusiness.config;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtProvider {
    private final Algorithm algorithm;

    @Value("${co.simplon.socwork.timeExp}")
    private Long time;

    JwtProvider(Algorithm algorithm) {
	this.algorithm = algorithm;
    }

    public String create(String subject) {
	Instant issuedAt = Instant.now();
	Builder builder = JWT.create().withIssuedAt(issuedAt).withSubject(subject);

	if (time > 0) {
	    builder.withExpiresAt(new Date(System.currentTimeMillis() + time));
	}
	return builder.sign(algorithm);
    }
}
