package org.aodocs.aodocassessmentapp.logic.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final Logger _logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${jwt.api.secret}")
    private String secretKey;
    private final int ttlInMinutes = 60;

    private JWTVerifier verifier;

    public String createJsonWebToken() {
        long nowMillis = System.currentTimeMillis();
        long expiresMillis = (ttlInMinutes * 60 * 1000) + nowMillis;
        final String issuer = "auth0";

        Date now = new Date(nowMillis);
        Date expiresDate = new Date(expiresMillis);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String token =  JWT.create()
                .withIssuedAt(now)
                .withIssuer(issuer)
                .withExpiresAt(expiresDate)
                .sign(algorithm);

        verifier = JWT.require(algorithm).withIssuer(issuer).build();
        _logger.info(token);

        return token;
    }

    public boolean verifyToken(String jwt) {
        try {
            if(jwt == null || jwt.length() < 8) {
                throw new JWTVerificationException("Invalid JWT");
            }
            jwt = jwt.substring(7); // Thrunk "Bearer "
            verifier.verify(jwt);
        } catch (JWTVerificationException e) {
            _logger.error("Error occurer during jwt token verification: {}", e.getMessage());
            return false;
        }

        return true;
    }
}