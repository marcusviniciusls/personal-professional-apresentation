package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
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
public class DeleteProfessionalExperienceServiceTest {

    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private DeleteProfessionalExperienceService deleteProfessionalExperienceService;
    @Autowired private ActivitiesRepository activitiesRepository;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalExperience professionalExperience = new ProfessionalExperience(id);
        professionalExperienceRepository.save(professionalExperience);
        Activities activities = new Activities();
        activities.setProfessionalExperience(professionalExperience);
        activities.setDescription("Graduacao");
        activitiesRepository.save(activities);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Professional Experience")
    public void deleteTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deleteProfessionalExperienceService.delete(id);

        Optional<ProfessionalExperience> optionalProfessionalExperience = professionalExperienceRepository.findById(id);
        Assertions.assertTrue(optionalProfessionalExperience.isEmpty());
    }
}
