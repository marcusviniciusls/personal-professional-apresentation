package br.com.marcus.dev.personal.professional.apresentation.services.user.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CreateUserRequest;
import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User convertRequestToEntity(CreateUserRequest createUserRequest){
        User user = modelMapper.map(createUserRequest, User.class);
        user.setUserCreation(user.getName());
        user.setDateCreation(LocalDateTime.now());
        user.setStatus(true);
        user.addProfile(Profile.ADMIN);
        String newPassword = bCryptPasswordEncoder.encode(createUserRequest.getPassword());
        user.setPassword(newPassword);
        return user;
    }
}
