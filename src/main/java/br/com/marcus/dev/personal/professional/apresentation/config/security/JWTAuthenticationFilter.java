package br.com.marcus.dev.personal.professional.apresentation.config.security;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException{
        try{
            LoginDto loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        } catch (IOException ioException){
            throw new RuntimeException(ioException);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filter,
                                            Authentication auth) throws IOException, ServletException{
        String email = ((UserSS) auth.getPrincipal()).getEmail();
        String token = jwtUtil.generateToken(email);
        response.addHeader("Authorization", "Bearer " + token);
    }

}
