package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
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
public class DeleteProfessionalGoalServiceTest {

    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private DeleteProfessionalGoalService deleteProfessionalGoalService;
    @Autowired private ActivitiesRepository activitiesRepository;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalGoal professionalGoal =
                new ProfessionalGoal("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        professionalGoal.setId(id);
        professionalGoalRepository.save(professionalGoal);
        Activities activities = new Activities();
        activities.setProfessionalGoal(professionalGoal);
        activities.setDescription("Professional Goal");
        activitiesRepository.save(activities);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Professional Goal")
    public void deleteTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deleteProfessionalGoalService.delete(id);

        Optional<ProfessionalGoal> optionalProfessionalGoal = professionalGoalRepository.findById(id);
        Assertions.assertTrue(optionalProfessionalGoal.isEmpty());
    }
}
