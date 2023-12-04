package com.makesoftware.siga.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class TokenService {

    private final String secret = "secret";
    private final Algorithm signingAlgorithm = Algorithm.HMAC256(secret);
    private final String issuer = "makesoftware";

    public String generateToken(UserDetails userDetails) {
        try {
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(userDetails.getUsername())
                    .withExpiresAt(generateExpirationDate())
                    .sign(signingAlgorithm);
        } catch (JWTCreationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(signingAlgorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
