package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdOfficeServiceTest {

    @Autowired private OfficeRepository officeRepository;
    @Autowired private FindByIdOfficeService findByIdOfficeService;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Office office = new Office(id, "Desenvolvedor Java", "description", OfficeLevel.SENIOR);
        officeRepository.save(office);

        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Office officeStatusFalse = new Office(idStatusFalse, "Desenvolvedor Java", "description", OfficeLevel.SENIOR);
        officeStatusFalse.setStatus(false);
        officeRepository.save(officeStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id Office")
    public void findByIdTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        OfficeResponse officeResponse = findByIdOfficeService.findById(id);
        // Teste Unitário
        Assertions.assertTrue(officeResponse != null);
        Assertions.assertEquals("Desenvolvedor Java", officeResponse.getName());
        Assertions.assertEquals("description", officeResponse.getDescription());
        Assertions.assertEquals(OfficeLevel.SENIOR, officeResponse.getOfficeLevel());
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id Office")
    public void findByIdEntity(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Office office = findByIdOfficeService.findByIdEntity(id);
        // Teste Unitário
        Assertions.assertTrue(office != null);
        Assertions.assertEquals("Desenvolvedor Java", office.getName());
        Assertions.assertEquals("description", office.getDescription());
        Assertions.assertEquals(OfficeLevel.SENIOR, office.getOfficeLevel());
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id Office - Status False")
    public void findByIdStatusFalseTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdOfficeService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id Office - Status False")
    public void findByIdStatusFalseEntity(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdOfficeService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id Office - Not Found")
    public void findByIdNotFoundTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdOfficeService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id Office - Not Found")
    public void findByIdNotFoundEntity(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdOfficeService.findByIdEntity(id));
    }
}
