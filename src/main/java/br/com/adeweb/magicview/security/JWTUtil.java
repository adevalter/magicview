package br.com.adeweb.magicview.security;

import br.com.adeweb.magicview.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;
    @Value("${security.jwt.expiration}")
    private Long expiration;
    @Value("${spring.application.name}")
    private String appName;

    public String generateToken(User user) throws IllegalArgumentException, JWTCreationException {

        return JWT.create()
                .withIssuer(appName)
                //.withArrayClaim("ROLES", List<String>.of())
                .withIssuedAt(new Date())
                .withExpiresAt(timeExpiration())
                //.withClaim("email", user.getEmail())
                .withClaim("user", user.getId().toString())
                .sign(Algorithm.HMAC256(secret));
    }

    public Date timeExpiration(){
        Date today = new Date();
        Date timeExpiration = new Date();
        timeExpiration.setTime(today.getTime() + expiration);
        return timeExpiration;
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                //.withIssuer(appName)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaim("user").asString();

    }
}
