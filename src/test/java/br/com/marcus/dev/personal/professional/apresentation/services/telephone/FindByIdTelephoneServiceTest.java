package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdTelephoneServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private FindByIdTelephoneService findByIdTelephoneService;

    @BeforeEach
    public void setupInit(){
        DataPersonal dataPersonal = new DataPersonal("Marcus", 27, LocalDate.of(1995,4,20),
                MaritalStatus.MARRIED);
        dataPersonalRepository.save(dataPersonal);
        Telephone telephone = new Telephone("55","11","993527709");
        telephone.setDataPersonal(dataPersonal);
        telephone.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        telephoneRepository.save(telephone);

        Telephone telephoneStatusFalse = new Telephone("55","11","993527709");
        telephoneStatusFalse.setDataPersonal(dataPersonal);
        telephoneStatusFalse.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"));
        telephoneStatusFalse.setStatus(false);
        telephoneRepository.save(telephoneStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Telephone por id")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        TelephoneDto telephoneDto = findByIdTelephoneService.findById(id);
        Assertions.assertTrue(telephoneDto != null);
        Assertions.assertEquals("55", telephoneDto.getDdi());
        Assertions.assertEquals("11", telephoneDto.getDdd());
        Assertions.assertEquals("993527709", telephoneDto.getNumber());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Telephone por id")
    public void findByIdTelephoneTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Telephone telephone = findByIdTelephoneService.findByIdTelephone(id);
        Assertions.assertTrue(telephone != null);
        Assertions.assertEquals("55", telephone.getDdi());
        Assertions.assertEquals("11", telephone.getDdd());
        Assertions.assertEquals("993527709", telephone.getNumber());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Telephone por id - Status False")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdTelephoneService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Telephone por id - Status False")
    public void findByIdTelephoneStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdTelephoneService.findByIdTelephone(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Telephone por id - Status False")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdTelephoneService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Telephone por id - Status False")
    public void findByIdTelephoneNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdTelephoneService.findByIdTelephone(id));
    }
}
