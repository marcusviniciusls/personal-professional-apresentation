package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
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
public class DeleteStudyPlanServiceTest {

    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private DeleteStudyPlanService deleteStudyPlanService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        StudyPlan studyPlan = new StudyPlan(id);
        studyPlanRepository.save(studyPlan);
    }

    @Test
    @Transactional
    @DisplayName("Apagar StudyPlan")
    public void deleteTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deleteStudyPlanService.delete(id);
        // Teste Unitário
        Optional<StudyPlan> optionalStudyPlan = studyPlanRepository.findById(id);
        Assertions.assertTrue(optionalStudyPlan.isEmpty());
    }
}
