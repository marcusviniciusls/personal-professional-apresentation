package br.com.marcus.dev.personal.professional.apresentation.config.security;

import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS authenticated(){
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e){
            return null;
        }
    }
}
