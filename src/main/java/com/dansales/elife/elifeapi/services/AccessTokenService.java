package com.dansales.elife.elifeapi.services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dansales.elife.elifeapi.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AccessTokenService {

    @Value("${api.security.secret}")
    private String secret;
    @Value("${spring.application.name}")
    private String appName;



    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer(appName)
                    .withSubject(user.getLogin())
                    .withExpiresAt(this.getExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Could not generate access token", exception);
        }
    }

    private Instant getExpires() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateAccessToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(this.appName)
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            return exception.getMessage();
        }
    }
}
