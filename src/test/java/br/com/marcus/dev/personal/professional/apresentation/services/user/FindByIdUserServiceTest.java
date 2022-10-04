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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("dev")
public class FindByIdUserServiceTest {

    @Autowired private FindByIdUserService findByIdUserService;
    @Autowired private TestEntityManager testEntityManager;
    @Autowired private UserRepository userRepository;

    @BeforeEach
    public void setupInit(){
        User user = new User();
        user.setUuid(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        testEntityManager.persist(user);
    }

    @Test
    @DisplayName("Buscar um Usuário com sucesso")
    public void findByIdTest(){
        User user = findByIdUserService.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(user != null).isTrue();
    }
}
