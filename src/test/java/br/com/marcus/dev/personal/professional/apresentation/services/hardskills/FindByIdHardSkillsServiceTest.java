package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdHardSkillsServiceTest {

    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private FindByIdHardSkillsService findByIdHardSkillsService;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Framework framework1 = new Framework("Quarkus", "description", "urlImage");
        Framework framework2 = new Framework("Spring Boot", "description", "urlImage");
        LanguageProgrammer language1 = new LanguageProgrammer("Java", "");
        LanguageProgrammer language2 = new LanguageProgrammer("C#", "");
        LanguageProgrammer language3 = new LanguageProgrammer("HTML", "");
        frameworkRepository.saveAll(Arrays.asList(framework1, framework2));
        languageProgrammerRepository.saveAll(Arrays.asList(language1, language2, language3));
        HardSkills hardSkills = new HardSkills("Java Programmer", "", Level.ADVANCED);
        hardSkills.setId(id);
        hardSkills.addListFramework(framework1);
        hardSkills.addListFramework(framework2);
        hardSkills.addListLanguageProgrammer(language1);
        hardSkills.addListLanguageProgrammer(language2);
        hardSkills.addListLanguageProgrammer(language3);
        hardSkillsRepository.save(hardSkills);

        HardSkills hardSkillsStatusFalse = new HardSkills("Java Programmer", "", Level.ADVANCED);
        hardSkillsStatusFalse.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376"));
        hardSkillsStatusFalse.setStatus(false);
        hardSkillsRepository.save(hardSkillsStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar HardSkills por Id")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        HardSkillsResponse response = findByIdHardSkillsService.findById(id);

        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Java Programmer", response.getName());
        Assertions.assertEquals("", response.getDescription());
        Assertions.assertEquals(Level.ADVANCED, response.getLevel());
        Assertions.assertEquals(id, response.getId());
        Assertions.assertEquals(2, response.getListFrameworkResponse().size());
        Assertions.assertEquals(3, response.getListLanguageProgrammerResponse().size());
    }

    @Test
    @Transactional
    @DisplayName("Buscar HardSkills por Id")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        HardSkills response = findByIdHardSkillsService.findByIdEntity(id);

        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Java Programmer", response.getName());
        Assertions.assertEquals("", response.getDescription());
        Assertions.assertEquals(Level.ADVANCED, response.getLevel());
        Assertions.assertEquals(id, response.getId());
        Assertions.assertEquals(2, response.getListFramework().size());
        Assertions.assertEquals(3, response.getListLanguage().size());
    }

    @Test
    @Transactional
    @DisplayName("Buscar HardSkills por Id - Status False")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdHardSkillsService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar HardSkills por Id - Status False")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdHardSkillsService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar HardSkills por Id - Not Found")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff377");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdHardSkillsService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar HardSkills por Id - Not Found")
    public void findByIdEntityNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff377");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdHardSkillsService.findByIdEntity(id));
    }
}
