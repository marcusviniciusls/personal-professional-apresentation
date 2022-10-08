package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.UserRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CheckSaveDataPersonalServiceTest {

    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private UserRepository userRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private CheckSaveDataPersonalService checkSaveDataPersonalService;

    @BeforeEach
    public void setupInit(){
        // User
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        User user = new User(id, LocalDateTime.now(), "Marcus", true, "Marcus"
                , "marcus@gmail.com");
        userRepository.save(user);

    }

    @Test
    @DisplayName("Verificar se já tem um Dados Pessoal cadastrado - Retornar False")
    public void verifyCheckSaveDataPersonalTest(){
        // Dados Mockados
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        User user = new User(id, LocalDateTime.now(), "Marcus", true, "Marcus"
                , "marcus@gmail.com");
        // E-mail
        Email emailPrincipal = new Email("viniciusmls@outlook.com");
        Email emailSecond = new Email("marcusvinicius@gmail.com");
        emailRepository.saveAll(Arrays.asList(emailPrincipal, emailSecond));
        // Telephone
        Telephone telephone = new Telephone("55","11", "32832654");
        telephoneRepository.save(telephone);
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.of(1995,4,20)
                , MaritalStatus.MARRIED);
        dataPersonal.setUser(user);
        dataPersonal.addListEmail(emailPrincipal);
        dataPersonal.addListEmail(emailSecond);
        dataPersonal.addListTelephone(telephone);
        dataPersonalRepository.save(dataPersonal);

        // Dados Mockados
        BDDMockito.given(centerEntityService.userLogged()).willReturn(user);

        // Executar Teste
        boolean verify = checkSaveDataPersonalService.verifyCheckSaveDataPersonal();

        // Teste Unitário
        Assertions.assertFalse(verify);
    }

    @Test
    @DisplayName("Verificar se já tem um Dados Pessoal cadastrado")
    public void verifyCheckSaveDataPersonalAlreadyExistsTest(){
        //Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        User user = new User(id, LocalDateTime.now(), "Marcus", true, "Marcus"
                , "marcus@gmail.com");
        // Dados Mockados
        BDDMockito.given(centerEntityService.userLogged()).willReturn(user);

        // Executar Teste
        boolean verify = checkSaveDataPersonalService.verifyCheckSaveDataPersonal();

        // Teste Unitário
        Assertions.assertTrue(verify);
    }
}
