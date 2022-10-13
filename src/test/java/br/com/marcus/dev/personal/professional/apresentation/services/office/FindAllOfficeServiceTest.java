package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllOfficeServiceTest {

    @Autowired private OfficeRepository officeRepository;
    @Autowired private FindAllOfficeService findAllOfficeService;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Office office = new Office(id, "Desenvolvedor Java", "description", OfficeLevel.SENIOR);
        officeRepository.save(office);
    }

    @Test
    @Transactional
    @DisplayName("Buscar todos os Office")
    public void findAllTest(){
        // Executando método
        Page<OfficeResponse> response = findAllOfficeService.findAll(PageRequest.of(1,1));
        // Teste Unitário
        Assertions.assertEquals(1, response.getSize());
    }
}
