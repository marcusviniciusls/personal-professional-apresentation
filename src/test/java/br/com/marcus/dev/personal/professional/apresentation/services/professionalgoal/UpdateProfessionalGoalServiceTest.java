package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalGoalUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateProfessionalGoalServiceTest {

    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private UpdateProfessionalGoalService updateProfessionalGoalService;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalGoal professionalGoal =
                new ProfessionalGoal("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        professionalGoal.setId(id);
        professionalGoalRepository.save(professionalGoal);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(professionalGoal);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Professional Goal")
    public void updateTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalGoalUpdateForm professionalGoalUpdateForm = new ProfessionalGoalUpdateForm("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        ProfessionalGoalResponse professionalGoalResponse = updateProfessionalGoalService.update(id, professionalGoalUpdateForm);
        Assertions.assertEquals("Ser o melhor Desenvolvedor Java", professionalGoalResponse.getDescription());
        Assertions.assertEquals("Desenvolvedor Java", professionalGoalResponse.getOffice());
    }
}
