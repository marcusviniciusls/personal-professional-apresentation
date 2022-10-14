package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalGoalSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalGoalUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfessionalGoalFactoryTest {

    @Autowired private ProfessionalGoalFactory professionalGoalFactory;

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Resposta (Professional Goal)")
    public void convertEntityInResponseTest(){
        ProfessionalGoal professionalGoal =
                new ProfessionalGoal("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        ProfessionalGoalResponse professionalGoalResponse = professionalGoalFactory.convertEntityInResponse(professionalGoal);
        Assertions.assertEquals("Ser o melhor Desenvolvedor Java", professionalGoalResponse.getDescription());
        Assertions.assertEquals("Desenvolvedor Java", professionalGoalResponse.getOffice());
    }

    @Test
    @Transactional
    @DisplayName("Converter SaveForm em Entidade (Professional Goal)")
    public void convertFormSaveInEntity(){
        ProfessionalGoalSaveForm professionalGoalSaveForm = new ProfessionalGoalSaveForm("teste", "office");
        ProfessionalGoal professionalGoal = professionalGoalFactory.convertFormSaveInEntity(professionalGoalSaveForm);
        Assertions.assertEquals("teste", professionalGoal.getDescription());
        Assertions.assertEquals("office", professionalGoal.getOffice());
    }

    @Test
    @Transactional
    @DisplayName("Converter UpdateForm em Entidade (Professional Goal)")
    public void convertFormUpdateInEntity(){
        ProfessionalGoal professionalGoal = new ProfessionalGoal("teste", "teste");
        ProfessionalGoalUpdateForm professionalGoalUpdateForm = new ProfessionalGoalUpdateForm("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        professionalGoal = professionalGoalFactory.convertFormUpdateInEntity(professionalGoalUpdateForm, professionalGoal);
        Assertions.assertEquals("Ser o melhor Desenvolvedor Java", professionalGoal.getDescription());
        Assertions.assertEquals("Desenvolvedor Java", professionalGoal.getOffice());
    }
}
