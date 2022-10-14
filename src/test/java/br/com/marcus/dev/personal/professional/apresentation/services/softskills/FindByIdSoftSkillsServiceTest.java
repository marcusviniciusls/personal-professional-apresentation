package br.com.marcus.dev.personal.professional.apresentation.services.softskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.FindByIdSoftSkillsService;
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
public class FindByIdSoftSkillsServiceTest {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private FindByIdSoftSkillsService findByIdSoftSkillsService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        SoftSkills softSkills = new SoftSkills("Boa comunicacao", true);
        softSkills.setId(id);
        softSkillsRepository.save(softSkills);

        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        SoftSkills softSkillsStatusFalse = new SoftSkills("Boa comunicacao", true);
        softSkillsStatusFalse.setId(id);
        softSkillsStatusFalse.setStatus(false);
        softSkillsStatusFalse.setId(idStatusFalse);
        softSkillsRepository.save(softSkillsStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar SoftSkills por ID")
    public void findByIdTest(){
        // Executando Teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        SoftSkillsResponse response = findByIdSoftSkillsService.findById(id);
        // Teste Unitário
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Boa comunicacao", response.getName());
        Assertions.assertEquals(true, response.isStatusHas());
    }

    @Test
    @Transactional
    @DisplayName("Buscar SoftSkills por ID")
    public void findByIdSoftSkillsTest(){
        // Executando Teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        SoftSkills softSkills = findByIdSoftSkillsService.findByIdSoftSkills(id);
        // Teste Unitário
        Assertions.assertTrue(softSkills != null);
        Assertions.assertEquals("Boa comunicacao", softSkills.getName());
        Assertions.assertEquals(true, softSkills.isStatusHas());
    }

    @Test
    @Transactional
    @DisplayName("Buscar SoftSkills por ID - Status False")
    public void findByIdStatusFalseTest(){
        // Executando Teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdSoftSkillsService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar SoftSkills por ID - Status False")
    public void findByIdSoftSkillsStatusFalseTest(){
        // Executando Teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdSoftSkillsService.findByIdSoftSkills(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar SoftSkills por ID - NotFound")
    public void findByIdNotFoundTest(){
        // Executando Teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdSoftSkillsService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar SoftSkills por ID - NotFound")
    public void findByIdSoftSkillsNotFoundTest(){
        // Executando Teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdSoftSkillsService.findByIdSoftSkills(id));
    }
}
