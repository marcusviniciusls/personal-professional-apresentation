package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorSavingRecordException;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.UserRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory.DataPersonalFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveDataPersonalServiceTest {

    @Autowired private SaveDataPersonalService saveDataPersonalService;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private UserRepository userRepository;
    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @MockBean private DataPersonalFactory dataPersonalFactory;
    @MockBean private CheckSaveDataPersonalService checkSaveDataPersonalService;

    @Test
    @DisplayName("Salvar Dados Pessoais , mas já existe um")
    public void saveErrorSavingRecordExceptionTest(){
        Email emailPrincipal = new Email("marcus.silva.dev@gmail.com");
        Email emailSecond = new Email("viniciusmls@outlook.com");
        emailRepository.saveAll(Arrays.asList(emailPrincipal, emailSecond));
        Telephone telephone1 = new Telephone("55", "11", "999989999");
        telephoneRepository.save(telephone1);
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.of(2022, 10,10), MaritalStatus.MARRIED);
        dataPersonal.addListTelephone(telephone1);
        dataPersonal.addListEmail(emailPrincipal);
        dataPersonal.addListEmail(emailSecond);
        User user = new User(UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff390"), LocalDateTime.now()
        , "Marcus", true, "Marcus", "viniciusmls@outlook.com");
        userRepository.save(user);
        dataPersonal.setUser(user);
        dataPersonalRepository.save(dataPersonal);
        EmailForm emailForm1 = new EmailForm("viniciusmls@outlook.com");
        EmailForm emailForm2 = new EmailForm("marcus.silva.dev@gmail.com");
        TelephoneForm telephoneForm = new TelephoneForm("55","11", "999999999");
        DataPersonalFullForm dataPersonalFullForm =
                new DataPersonalFullForm("Marcus Vinicius", "20/04/1995", 2);
        dataPersonalFullForm.addListEmailForm(emailForm1);
        dataPersonalFullForm.addListEmailForm(emailForm2);
        dataPersonalFullForm.addListTelephoneForm(telephoneForm);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(dataPersonal);
        BDDMockito.given(centerEntityService.userLogged()).willReturn(user);
        Assertions.assertThrows(ErrorSavingRecordException.class, () -> saveDataPersonalService.save(dataPersonalFullForm));
    }

    @Test
    @DisplayName("Salvar Dados Pessoais com sucesso")
    public void saveTest(){
        // Entidade
        Email emailPrincipal = new Email("marcus.silva.dev@gmail.com");
        Email emailSecond = new Email("viniciusmls@outlook.com");
        Telephone telephone1 = new Telephone("55", "11", "999989999");
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.of(2022, 10,10), MaritalStatus.MARRIED);
        dataPersonal.addListTelephone(telephone1);
        dataPersonal.addListEmail(emailPrincipal);
        dataPersonal.addListEmail(emailSecond);
        User user = new User(UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff390"), LocalDateTime.now()
                , "Marcus", true, "Marcus", "viniciusmls@outlook.com");
        dataPersonal.setUser(user);
        // Dados do Request
        EmailForm emailForm1 = new EmailForm("viniciusmls@outlook.com");
        EmailForm emailForm2 = new EmailForm("marcus.silva.dev@gmail.com");
        TelephoneForm telephoneForm = new TelephoneForm("55","11", "999999999");
        DataPersonalFullForm dataPersonalFullForm =
                new DataPersonalFullForm("Marcus Vinicius", "20/04/1995", 2);
        dataPersonalFullForm.addListEmailForm(emailForm1);
        dataPersonalFullForm.addListEmailForm(emailForm2);
        dataPersonalFullForm.addListTelephoneForm(telephoneForm);
        // Dados de Resposta
        EmailDto emailDto1 = new EmailDto("marcus.silva.dev@gmail.com");
        EmailDto emailDto2 = new EmailDto("viniciusmls@outlook.com");
        TelephoneDto telephoneDto = new TelephoneDto("55", "11", "999989999");
        DataPersonalDto dataPersonalDto = new DataPersonalDto("Marcus Vinicius", 27, "MARRIED", LocalDate.of(1995, 4, 20));
        dataPersonalDto.addListTelephoneDto(telephoneDto);
        dataPersonalDto.addListEmailDto(emailDto1);
        dataPersonalDto.addListEmailDto(emailDto2);

        // Executar testes unitários
        BDDMockito.given(dataPersonalFactory.convertDtoInEntitySave(Mockito.any(DataPersonalFullForm.class))).willReturn(dataPersonal);
        BDDMockito.given(checkSaveDataPersonalService.verifyCheckSaveDataPersonal()).willReturn(true);
        BDDMockito.given(dataPersonalFactory.convertEntityInDto(Mockito.any(DataPersonal.class))).willReturn(dataPersonalDto);
        DataPersonalDto response = saveDataPersonalService.save(dataPersonalFullForm);
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Marcus Vinicius", response.getFullname());
        Assertions.assertEquals(27, response.getAge());
        Assertions.assertEquals(LocalDate.of(1995, 4,20), response.getBirthDate());
        Assertions.assertEquals("MARRIED", response.getMartialStatus());
        Assertions.assertTrue(response.getListEmailDto().size() == 2);
        Assertions.assertTrue(response.getListTelephoneDto().size() == 1);
    }
}
