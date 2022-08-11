package br.com.marcus.dev.personal.professional.apresentation.services;

import br.com.marcus.dev.personal.professional.apresentation.config.security.UserSS;
import br.com.marcus.dev.personal.professional.apresentation.config.security.UserService;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.services.user.FindByIdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CenterEntityService {

    @Autowired private FindByIdUserService findByIdUserService;

    public SuperEntity setDataToSave(SuperEntity entity){
        UserSS userSS = UserService.authenticated();
        User user = findByIdUserService.findById(userSS.getId());
        entity.setUser(user);
        entity.setUserCreation(user.getName());
        entity.setDateCreation(LocalDateTime.now());
        return entity;
    }
}
