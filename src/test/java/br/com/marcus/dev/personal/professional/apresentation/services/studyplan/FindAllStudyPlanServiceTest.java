package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.StudyPlanResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
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
public class FindAllStudyPlanServiceTest {

    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private FindAllStudyPlanService findAllStudyPlanService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        StudyPlan studyPlan = new StudyPlan(id);
        studyPlanRepository.save(studyPlan);
    }

    @Test
    @Transactional
    @DisplayName("Buscar todos os StudyPlan")
    public void findAllTest(){
        Page<StudyPlanResponse> response = findAllStudyPlanService.findAll(PageRequest.of(1,1));
        Assertions.assertEquals(1, response.getSize());
    }
}
