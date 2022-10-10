package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
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
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteEmailServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private DeleteEmailService deleteEmailService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        dataPersonalRepository.save(dataPersonal);
        Email email = new Email(id, "viniciusmls@outlook.com", dataPersonal);
        emailRepository.save(email);
    }

    @Test
    @DisplayName("Apagar E-mail com sucesso")
    public void deleteTest(){
        //Executar o método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        deleteEmailService.delete(id);
        Optional<Email> optionalEmail = emailRepository.findById(id);
        // Verificar teste
        Assertions.assertTrue(optionalEmail.isEmpty());
    }
}
