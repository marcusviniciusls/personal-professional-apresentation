package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
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
public class SaveEmailServiceTest {

    @Autowired private SaveEmailService saveEmailService;
    @Autowired private DataPersonalRepository dataPersonalRepository;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        dataPersonal.setId(id);
        dataPersonalRepository.save(dataPersonal);
    }

    @Test
    @DisplayName("Salvar E-mail com sucesso")
    public void saveTest(){
        // Dados Mockados
        Email email = new Email("viniciusmls@outlook.com");
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(email);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        // Dados de preparacao
        String id = "cb260da4-01fb-48f0-aec4-d7f9db2ff301";
        EmailFormSave emailFormSave = new EmailFormSave("viniciusmls@outlook.com", id);

        // Executar método
        EmailDto response = saveEmailService.save(emailFormSave);

        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("viniciusmls@outlook.com", response.getEmail());
    }
}
