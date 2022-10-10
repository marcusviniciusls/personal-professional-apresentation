package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdDataPersonalServiceTest {

    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private FindByIdDataPersonalService findByIdDataPersonalService;

    @BeforeEach
    public void setupInit(){
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
        DataPersonal dataPersonalStatusFalse = new DataPersonal("Marcus Vinicius", 27, LocalDate.of(2022, 10,10), MaritalStatus.MARRIED);
        dataPersonalStatusFalse.addListEmail(emailPrincipal);
        dataPersonalStatusFalse.addListEmail(emailSecond);
        dataPersonalStatusFalse.addListTelephone(telephone1);
        dataPersonalStatusFalse.addListTelephone(telephone2);
        dataPersonalStatusFalse.addListTelephone(telephone3);
        dataPersonalStatusFalse.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff302"));
        dataPersonalStatusFalse.setStatus(false);
        dataPersonalRepository.save(dataPersonalStatusFalse);
        dataPersonalRepository.save(dataPersonal);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Dados Pessoais por Id Com sucesso")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff350");
        DataPersonalDto response = findByIdDataPersonalService.findById(id);
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Marcus Vinicius", response.getFullname());
        Assertions.assertEquals(27, response.getAge());
        Assertions.assertEquals(LocalDate.of(2022, 10,10), response.getBirthDate());
        Assertions.assertEquals(MaritalStatus.MARRIED.getNumber(), response.getMartialStatus());
        Assertions.assertTrue(response.getListEmailDto().size() == 2);
        Assertions.assertTrue(response.getListTelephoneDto().size() == 3);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Dados Pessoais por Id Com sucesso")
    public void findByIdDataPersonalTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff350");
        DataPersonal response = findByIdDataPersonalService.findByIdDataPersonal(id);
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Marcus Vinicius", response.getFullName());
        Assertions.assertEquals(27, response.getAge());
        Assertions.assertEquals(LocalDate.of(2022, 10,10), response.getBirthDate());
        Assertions.assertEquals(MaritalStatus.MARRIED, response.getMaritalStatus());
        Assertions.assertTrue(response.getListEmail().size() == 2);
        Assertions.assertTrue(response.getListTelephone().size() == 3);
    }

    @Test
    @DisplayName("Buscar Dados Pessoais por ID e retornar erro Not Found")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff100");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdDataPersonalService.findById(id));
    }

    @Test
    @DisplayName("Buscar Dados Pessoais por ID e retornar erro Not Found")
    public void findByIdDataPersonalNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff100");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdDataPersonalService.findByIdDataPersonal(id));
    }

    @Test
    @DisplayName("Buscar Dados Pessoais por ID e retornar erro Status False")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff302");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdDataPersonalService.findById(id));
    }

    @Test
    @DisplayName("Buscar Dados Pessoais por ID e retornar erro Status False")
    public void findByIdDataPersonalStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff302");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdDataPersonalService.findByIdDataPersonal(id));
    }
}
