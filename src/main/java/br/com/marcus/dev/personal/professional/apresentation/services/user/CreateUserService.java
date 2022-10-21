package br.com.marcus.dev.personal.professional.apresentation.services.user;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CreateUserRequest;
import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.repository.UserRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.user.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    @Autowired private UserFactory userFactory;
    @Autowired private UserRepository userRepository;

    public void createUser(CreateUserRequest createUserRequest){
        User user = userFactory.convertRequestToEntity(createUserRequest);
        userRepository.save(user);
    }
}
