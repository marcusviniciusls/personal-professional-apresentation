package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DataPersonalFactoryTest {

    @Autowired private DataPersonalFactory dataPersonalFactory;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private DataPersonalRepository dataPersonalRepository;

    @Test
    @DisplayName("Converter Entidade em Dto (DataPersonal)")
    public void convertEntityInDtoTest(){
        // Entidade
        Email emailPrincipal = new Email("marcus.silva.dev@gmail.com");
        Email emailSecond = new Email("viniciusmls@outlook.com");
        Telephone telephone1 = new Telephone("55", "11", "999989999");
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.of(1995, 4,20), MaritalStatus.MARRIED);
        dataPersonal.addListTelephone(telephone1);
        dataPersonal.addListEmail(emailPrincipal);
        dataPersonal.addListEmail(emailSecond);

        // Executar Testes
        DataPersonalDto response = dataPersonalFactory.convertEntityInDto(dataPersonal);

        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Marcus Vinicius", response.getFullname());
        Assertions.assertEquals(27, response.getAge());
        Assertions.assertEquals(LocalDate.of(1995, 4,20), response.getBirthDate());
        Assertions.assertEquals(MaritalStatus.MARRIED.getNumber(), response.getMartialStatus());
        Assertions.assertTrue(response.getListEmailDto().size() == 2);
        Assertions.assertTrue(response.getListTelephoneDto().size() == 1);
    }

    @Test
    @DisplayName("Retono de dias")
    public void returnDayTest(){
        String date = "20/04/1995";
        Integer day = dataPersonalFactory.returnDay(date);
        Assertions.assertEquals(20, day);
    }

    @Test
    @DisplayName("Retono de Meses")
    public void returnMonthTest(){
        String date = "20/04/1995";
        Integer month = dataPersonalFactory.returnMonth(date);
        Assertions.assertEquals(4, month);
    }

    @Test
    @DisplayName("Retono de Anos")
    public void returnYearTest(){
        String date = "20/04/1995";
        Integer year = dataPersonalFactory.returnYear(date);
        Assertions.assertEquals(1995, year);
    }
}
