package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdEmailServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private FindByIdEmailService findByIdEmailService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        dataPersonalRepository.save(dataPersonal);
        Email email = new Email(id, "viniciusmls@outlook.com", dataPersonal);
        Email emailStatusFalse = new Email(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302"), "viniciusmls@outlook.com", dataPersonal);
        emailStatusFalse.setStatus(false);
        emailRepository.save(email);
        emailRepository.save(emailStatusFalse);
    }

    @Test
    @DisplayName("Buscar E-mail por Id")
    public void findByIdTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        EmailDto emailDto = findByIdEmailService.findById(id);
        // Teste Unitários
        Assertions.assertEquals("viniciusmls@outlook.com", emailDto.getEmail());
        Assertions.assertTrue(emailDto != null);
    }

    @Test
    @DisplayName("Buscar E-mail por Id")
    public void findByIdEmailTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Email email = findByIdEmailService.findByIdEmail(id);
        // Teste Unitários
        Assertions.assertEquals("viniciusmls@outlook.com", email.getEmail());
        Assertions.assertTrue(email != null);
    }

    @Test
    @DisplayName("Buscar E-mail por Id Status False")
    public void findByIdStatusFalseTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdEmailService.findById(id));
    }

    @Test
    @DisplayName("Buscar E-mail por Id Status False")
    public void findByIdEmailStatusFalseTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdEmailService.findByIdEmail(id));
    }

    @Test
    @DisplayName("Buscar E-mail por Id Not Found")
    public void findByIdNotFoundTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdEmailService.findById(id));
    }

    @Test
    @DisplayName("Buscar E-mail por Id Not Found")
    public void findByIdEmailNotFoundTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdEmailService.findByIdEmail(id));
    }
}
