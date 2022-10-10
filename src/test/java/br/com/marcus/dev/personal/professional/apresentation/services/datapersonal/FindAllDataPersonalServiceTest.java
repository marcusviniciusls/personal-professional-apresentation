package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllDataPersonalServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private FindAllDataPersonalService findAllDataPersonalService;

    @BeforeEach
    public void setupInit(){
        Email emailPrincipal = new Email("marcus.silva.dev@gmail.com");
        Email emailSecond = new Email("viniciusmls@outlook.com");
        emailRepository.saveAll(Arrays.asList(emailPrincipal, emailSecond));
        Telephone telephone1 = new Telephone("55", "11", "999989999");
        Telephone telephone2 = new Telephone("55", "11", "999989999");
        Telephone telephone3 = new Telephone("55", "11", "999989999");
        telephoneRepository.saveAll(Arrays.asList(telephone1, telephone2, telephone3));
        DataPersonal dataPersonal1 = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        DataPersonal dataPersonal2 = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        DataPersonal dataPersonal3 = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        DataPersonal dataPersonal4 = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        DataPersonal dataPersonal5 = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        dataPersonal1.addListEmail(emailPrincipal);
        dataPersonal1.addListEmail(emailSecond);
        dataPersonal1.addListTelephone(telephone1);
        dataPersonal1.addListTelephone(telephone2);
        dataPersonal1.addListTelephone(telephone3);
        dataPersonal2.addListEmail(emailPrincipal);
        dataPersonal2.addListEmail(emailSecond);
        dataPersonal2.addListTelephone(telephone1);
        dataPersonal2.addListTelephone(telephone2);
        dataPersonal2.addListTelephone(telephone3);
        dataPersonal3.addListEmail(emailPrincipal);
        dataPersonal3.addListEmail(emailSecond);
        dataPersonal3.addListTelephone(telephone1);
        dataPersonal3.addListTelephone(telephone2);
        dataPersonal3.addListTelephone(telephone3);
        dataPersonal4.addListEmail(emailPrincipal);
        dataPersonal4.addListEmail(emailSecond);
        dataPersonal4.addListTelephone(telephone1);
        dataPersonal4.addListTelephone(telephone2);
        dataPersonal4.addListTelephone(telephone3);
        dataPersonal5.addListEmail(emailPrincipal);
        dataPersonal5.addListEmail(emailSecond);
        dataPersonal5.addListTelephone(telephone1);
        dataPersonal5.addListTelephone(telephone2);
        dataPersonal5.addListTelephone(telephone3);
        dataPersonalRepository.saveAll(Arrays.asList(dataPersonal1, dataPersonal2, dataPersonal3, dataPersonal4, dataPersonal5));
    }

    @Test
    @Transactional
    @DisplayName("Buscar todos os Dados Pessoais")
    public void findAllTest(){
        Page<DataPersonalDto> page = findAllDataPersonalService.findAll(PageRequest.of(1,5));
        Assertions.assertEquals(5, page.getSize());
    }
}
