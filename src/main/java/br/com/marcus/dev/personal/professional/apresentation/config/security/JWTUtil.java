package br.com.marcus.dev.personal.professional.apresentation.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    public String generateToken(String email){
        Long expirationLong = Long.parseLong(expiration);
        Date dateExpiration = new Date();
        dateExpiration.setTime(System.currentTimeMillis() + expirationLong);
        return Jwts.builder().setSubject(email).
                setExpiration(dateExpiration).
                signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }
}
