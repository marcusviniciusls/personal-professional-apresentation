package br.com.marcus.dev.personal.professional.apresentation.services.generalrule;

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
    @Autowired private UserService userService;

    public SuperEntity setDataToSave(SuperEntity entity){
        UserSS userSS = userService.authenticated();
        User user = findByIdUserService.findById(userSS.getId());
        entity.setUser(user);
        entity.setUserCreation(user.getName());
        entity.setDateCreation(LocalDateTime.now());
        return entity;
    }

    public SuperEntity setDataToUpdate(SuperEntity entity){
        UserSS userSS = userService.authenticated();
        User user = findByIdUserService.findById(userSS.getId());
        entity.setUserUpdate(user.getName());
        entity.setDateUpdate(LocalDateTime.now());
        return entity;
    }

    public SuperEntity setDataToDelete(SuperEntity entity){
        UserSS userSS = userService.authenticated();
        User user = findByIdUserService.findById(userSS.getId());
        entity.setStatus(false);
        entity.setDateUpdate(LocalDateTime.now());
        entity.setUserUpdate(user.getName());
        return entity;
    }

    public boolean isStatusSuperEntity(SuperEntity entity){
        return entity.isStatus();
    }

    public User userLogged(){
        UserSS userSS = userService.authenticated();
        User user = findByIdUserService.findById(userSS.getId());
        return user;
    }
}
