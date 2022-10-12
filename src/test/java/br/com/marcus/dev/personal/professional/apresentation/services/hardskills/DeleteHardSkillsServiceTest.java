package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.DeleteActivitiesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteHardSkillsServiceTest {

    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private DeleteHardSkillsService deleteHardSkillsService;
    @MockBean private DeleteActivitiesService deleteActivitiesService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        HardSkills hardSkills = new HardSkills("Java Programmer", "", Level.ADVANCED);
        hardSkills.setId(id);
        hardSkillsRepository.save(hardSkills);
        BDDMockito.given(deleteActivitiesService.deleteMovementHardSkills(Mockito.any(UUID.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Apagar HardSkills")
    public void DeleteGraduationServiceTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        deleteHardSkillsService.delete(id);

        // Teste Unitários
        Optional<HardSkills> optionalHardSkills = hardSkillsRepository.findById(id);
        Assertions.assertTrue(optionalHardSkills.isEmpty());
    }
}
