package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateEmailServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private UpdateEmailService updateEmailService;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        dataPersonalRepository.save(dataPersonal);
        Email email = new Email(id, "viniciusmls@outlook.com", dataPersonal);
        emailRepository.save(email);
    }

    @Test
    @DisplayName("Atualizar E-mail com sucesso")
    public void updateTest(){
        // Dados Mockados
        Email email = new Email("teste@gmail.com");
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(email);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        EmailFormUpdate emailFormUpdate = new EmailFormUpdate("teste@gmail.com");
        // Executar método
        EmailDto resposne = updateEmailService.update(emailFormUpdate, id);
        // Teste Unitários
        Assertions.assertTrue(resposne != null);
        Assertions.assertEquals("teste@gmail.com", resposne.getEmail());
    }
}
