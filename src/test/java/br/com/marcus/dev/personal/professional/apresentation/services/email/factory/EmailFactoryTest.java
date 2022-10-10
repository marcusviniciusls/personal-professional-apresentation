package br.com.marcus.dev.personal.professional.apresentation.services.email.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmailFactoryTest {

    @Autowired private EmailFactory emailFactory;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @DisplayName("Converter Entidade em Dto (E-Mail)")
    public void convertEntityInDtoTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Email email = new Email("viniciusmls@outlook.com");
        // Executar método
        EmailDto response = emailFactory.convertEntityInDto(email);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("viniciusmls@outlook.com", response.getEmail());
    }

    @Test
    @DisplayName("Converter Form em Entidade (E-Mail)")
    public void convertFormInEntityTest(){
        // Dados de Entrada
        String email = "viniciusmls@outlook.com";
        // Executar método
        Email entidade = emailFactory.convertFormInEntity(email);
        // Testes Unitários
        Assertions.assertTrue(entidade != null);
        Assertions.assertEquals("viniciusmls@outlook.com", entidade.getEmail());
    }

    @Test
    @DisplayName("Converter Entidade em Update (E-Mail)")
    public void convertFormInEntityUpdateTest(){
        // Dados de Entrada
        String emailForm = "viniciusmls@outlook.com";
        Email email = new Email("teste@gmail.com");
        // Executar método
        Email entidade = emailFactory.convertFormInEntityUpdate(emailForm, email);
        // Testes Unitários
        Assertions.assertTrue(entidade != null);
        Assertions.assertEquals("viniciusmls@outlook.com", entidade.getEmail());
    }

    @Test
    @DisplayName("Converter Dto em Lista de Entidade (E-Mail)")
    public void convertDtoInEntityListSaveTest(){
        // Dados de Entrada
        Email email = new Email("teste@gmail.com");
        EmailForm emailForm1 = new EmailForm("teste@gmail.com");
        EmailForm emailForm2 = new EmailForm("test@gmail.com");
        List<EmailForm> listEmailForm = new ArrayList<>();
        listEmailForm.add(emailForm1);
        listEmailForm.add(emailForm2);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(email);
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        // Executar método
        List<Email> listEmail = emailFactory.convertDtoInEntityListSave(listEmailForm, dataPersonal);
        // Testes Unitários
        Assertions.assertTrue(listEmail != null);
        Assertions.assertEquals(2, listEmail.size());
    }
}
