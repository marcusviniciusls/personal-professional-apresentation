package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteDataPersonalServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private DeleteDataPersonalService deleteDataPersonalService;

    @Test
    @Transactional
    @DisplayName("Apagar Dados Pessoais")
    public void deleteTest(){
        // Dados de Entrada
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", 27, LocalDate.now(), MaritalStatus.MARRIED);
        Email emailPrincipal = new Email("marcus.silva.dev@gmail.com");
        Email emailSecond = new Email("viniciusmls@outlook.com");
        emailRepository.saveAll(Arrays.asList(emailPrincipal, emailSecond));
        Telephone telephone1 = new Telephone("55", "11", "999989999");
        Telephone telephone2 = new Telephone("55", "11", "999989999");
        Telephone telephone3 = new Telephone("55", "11", "999989999");
        telephoneRepository.saveAll(Arrays.asList(telephone1, telephone2, telephone3));
        dataPersonal.addListEmail(emailPrincipal);
        dataPersonal.addListEmail(emailSecond);
        dataPersonal.addListTelephone(telephone1);
        dataPersonal.addListTelephone(telephone2);
        dataPersonal.addListTelephone(telephone3);
        dataPersonal.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff335"));
        dataPersonalRepository.save(dataPersonal);
        // Dados Mockados
        BDDMockito.given(centerEntityService.isStatusSuperEntity(dataPersonal)).willReturn(true);
        // Executar teste
        deleteDataPersonalService.delete(UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff335"));
        // Verificar Dados
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d1f9db2ff335");
        Optional<DataPersonal> optionalDataPersonal = dataPersonalRepository.findById(id);
        Assertions.assertTrue(optionalDataPersonal.isEmpty());
    }
}
