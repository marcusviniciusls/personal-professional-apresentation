package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory.DataPersonalFactory;
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
import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateDataPersonalServiceTest {

    @Autowired private UpdateDataPersonalService updateDataPersonalService;
    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private DataPersonalRepository dataPersonalRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private DataPersonalFactory dataPersonalFactory;

    @Test
    @DisplayName("Atualizar Dados Pessoais")
    public void updateTest(){
        // Dados de entidades
        Email emailPrincipal = new Email("marcus.silva.dev@gmail.com");
        Email emailSecond = new Email("viniciusmls@outlook.com");
        emailRepository.saveAll(Arrays.asList(emailPrincipal, emailSecond));
        Telephone telephone1 = new Telephone("55", "11", "999989999");
        Telephone telephone2 = new Telephone("55", "11", "999989999");
        Telephone telephone3 = new Telephone("55", "11", "999989999");
        telephoneRepository.saveAll(Arrays.asList(telephone1, telephone2, telephone3));
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.of(2022, 10,10), MaritalStatus.MARRIED);
        dataPersonal.addListEmail(emailPrincipal);
        dataPersonal.addListEmail(emailSecond);
        dataPersonal.addListTelephone(telephone1);
        dataPersonal.addListTelephone(telephone2);
        dataPersonal.addListTelephone(telephone3);
        dataPersonal.setStatus(true);
        dataPersonal.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff350"));
        dataPersonalRepository.save(dataPersonal);
        // Dados de Resposta
        EmailDto emailDto1 = new EmailDto("marcus.silva.dev@gmail.com");
        EmailDto emailDto2 = new EmailDto("viniciusmls@outlook.com");
        TelephoneDto telephoneDto = new TelephoneDto("55", "11", "999989999");
        DataPersonalDto dataPersonalDto = new DataPersonalDto("Marcus Vinicius", 27, "MARRIED", LocalDate.of(1995, 4, 20));
        dataPersonalDto.addListTelephoneDto(telephoneDto);
        dataPersonalDto.addListEmailDto(emailDto1);
        dataPersonalDto.addListEmailDto(emailDto2);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(dataPersonal);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(dataPersonalFactory.convertDtoInEntityUpdate(Mockito.any(DataPersonalFullFormUpdate.class), Mockito.any(DataPersonal.class))).willReturn(dataPersonal);
        BDDMockito.given(dataPersonalFactory.convertEntityInDto(Mockito.any(DataPersonal.class))).willReturn(dataPersonalDto);

        // Executar testes
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff350");
        DataPersonalFullFormUpdate request = new DataPersonalFullFormUpdate("Marcus Vinicius", "20/04/1995", 1);
        DataPersonalDto response = updateDataPersonalService.update(request, id);
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Marcus Vinicius", response.getFullname());
        Assertions.assertEquals(LocalDate.of(1995, 4, 20), response.getBirthDate());
        Assertions.assertEquals("MARRIED", response.getMartialStatus());
    }
}
