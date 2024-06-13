package com.dansales.elife.elifeapi.services.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.dansales.elife.elifeapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment env;

    private  String twoFactorSecret;

    public AccessTokenService () {
        if (env == null) {
            this.secret = "grand_line_H@shcode";
        } else {
            this.twoFactorSecret = env.getProperty("api.security.twoFactorSecret");
        }
    }

    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return  JWT.create()
                    .withIssuer(appName)
                    .withSubject(user.getLogin())
                    .withExpiresAt(this.getExpires(null))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Could not generate access token", exception);
        }
    }

    public String generateTwoFactorToken(User user) {
        try {
            if (this.twoFactorSecret == null) {
                this.twoFactorSecret ="grand_line_H@shcode";
            }
            Algorithm algorithm = Algorithm.HMAC256(twoFactorSecret);
            return  JWT.create()
                    .withIssuer(appName)
                    .withSubject(user.getLogin())
                    .withExpiresAt(this.getExpires(1))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Could not generate access token", exception);
        }
    }

    private Instant getExpires(Integer expireTimeInHours) {
        if (expireTimeInHours == null) {
            expireTimeInHours = 3;
        }
        return LocalDateTime.now().plusHours(expireTimeInHours).toInstant(ZoneOffset.of("-03:00"));
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
    public String validateVerificationToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(twoFactorSecret);
            return JWT.require(algorithm)
                    .withIssuer(this.appName)
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            return exception.getMessage();
        }
    }
}
