package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
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
public class FindAllHardSkillsServiceTest {

    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private FindAllHardSkillsService findAllHardSkillsService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        HardSkills hardSkills = new HardSkills("Java Programmer", "", Level.ADVANCED);
        hardSkills.setId(id);
        hardSkillsRepository.save(hardSkills);
    }

    @Test
    @Transactional
    @DisplayName("Buscar todos os HardSkills")
    public void findAllTest(){
        Page<HardSkillsResponse> response = findAllHardSkillsService.findAll(PageRequest.of(1,1));
        Assertions.assertEquals(1 , response.getSize());
    }
}
