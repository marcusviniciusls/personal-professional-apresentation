package br.com.marcus.dev.personal.professiona.apresentaion.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    public String generateToken(String email){
        return Jwts.builder().setSubject(email).
                //setExpiration(new Date(System.currentTimeMillis() + expiration)).
                signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }
}
