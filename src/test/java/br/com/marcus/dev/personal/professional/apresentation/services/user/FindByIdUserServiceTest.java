package br.com.marcus.dev.personal.professional.apresentation.services.user;

import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;

import br.com.marcus.dev.personal.professional.apresentation.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdUserServiceTest {

    @Autowired private FindByIdUserService findByIdUserService;
    @Autowired private UserRepository userRepository;

    @BeforeEach
    public void setupInit(){
        User user = new User();
        user.setUuid(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        userRepository.save(user);
    }

    @Test
    @DisplayName("Buscar um Usuário com sucesso")
    public void findByIdTest(){
        User user = findByIdUserService.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(user != null).isTrue();
    }

    @Test
    @DisplayName("Buscar um Usuário e nao encontrar")
    public void findByIdNotFoundTest(){
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> findByIdUserService.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702")))
                .withMessage("ID Not Found Exception");
    }
}
