package br.com.marcus.dev.personal.professiona.apresentaion.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String LongExpiration;

    public String generateToken(String email){
        return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + LongExpiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
    }
}
