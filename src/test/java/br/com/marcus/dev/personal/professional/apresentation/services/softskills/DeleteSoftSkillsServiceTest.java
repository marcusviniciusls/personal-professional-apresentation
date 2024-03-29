package br.com.marcus.dev.personal.professional.apresentation.services.softskills;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.DeleteSoftSkillsService;
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
public class DeleteSoftSkillsServiceTest {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private DeleteSoftSkillsService deleteSoftSkillsService;
    @Autowired private ActivitiesRepository activitiesRepository;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        SoftSkills softSkills = new SoftSkills("Boa comunicacao", true);
        softSkills.setId(id);
        softSkillsRepository.save(softSkills);
        Activities activities = new Activities();
        activities.setSoftSkills(softSkills);
        activities.setDescription("SoftSkills");
        activitiesRepository.save(activities);
    }

    @Test
    @Transactional
    @DisplayName("Apagar SoftSkills")
    public void deleteTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deleteSoftSkillsService.delete(id);
        // Teste Unitário
        Optional<SoftSkills> optionalSoftSkills = softSkillsRepository.findById(id);
        Assertions.assertTrue(optionalSoftSkills.isEmpty());
    }
}
