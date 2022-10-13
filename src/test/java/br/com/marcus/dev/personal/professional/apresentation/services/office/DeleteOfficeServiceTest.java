package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
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
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteOfficeServiceTest {

    @Autowired private OfficeRepository officeRepository;
    @Autowired private DeleteOfficeService deleteOfficeService;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Office office = new Office(id, "Desenvolvedor Java", "description", OfficeLevel.SENIOR);
        officeRepository.save(office);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Office")
    public void deleteTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        deleteOfficeService.delete(id);

        // Teste Unitário
        Optional<Office> optionalOffice = officeRepository.findById(id);
        Assertions.assertTrue(optionalOffice.isEmpty());
    }
}
