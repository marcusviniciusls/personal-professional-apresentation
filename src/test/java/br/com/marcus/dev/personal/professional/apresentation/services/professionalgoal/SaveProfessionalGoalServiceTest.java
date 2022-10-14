package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalGoalSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveProfessionalGoalServiceTest {

    @Autowired private SaveProfessionalGoalService saveProfessionalGoalService;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SaveActivitiesService saveActivitiesService;

    @Test
    @Transactional
    @DisplayName("Salvar Professional Goal")
    public void saveTest(){
        ProfessionalGoal professionalGoal = new ProfessionalGoal("teste", "office");
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(professionalGoal);
        BDDMockito.given(saveActivitiesService.saveMovementProfessionalGoal(Mockito.any(ProfessionalGoal.class))).willReturn(true);
        ProfessionalGoalSaveForm professionalGoalSaveForm = new ProfessionalGoalSaveForm("teste", "office");
        ProfessionalGoalResponse response = saveProfessionalGoalService.save(professionalGoalSaveForm);
        Assertions.assertEquals("teste", response.getDescription());
        Assertions.assertEquals("office", response.getOffice());
        Assertions.assertTrue(response != null);
    }
}
