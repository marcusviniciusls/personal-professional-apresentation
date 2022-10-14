package br.com.marcus.dev.personal.professional.apresentation.services.softskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.SaveSoftSkillsService;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveSoftSkillsServiceTest {

    @MockBean private CenterEntityService centerEntityService;
    @Autowired private SaveSoftSkillsService saveSoftSkillsService;
    @MockBean private SaveActivitiesService saveActivitiesService;

    @BeforeEach
    public void setupInit(){
        SoftSkills softSkills = new SoftSkills("Boa comunicacao", true);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(softSkills);

        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(saveActivitiesService.saveMovementSoftSkills(Mockito.any(SoftSkills.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Salvar SoftSkills")
    public void saveTest(){
        // Executando método
        SoftSkillsFormSave softSkillsFormSave = new SoftSkillsFormSave("Boa comunicacao");
        SoftSkillsResponse response = saveSoftSkillsService.save(softSkillsFormSave);
        // Teste Unitário
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Boa comunicacao", response.getName());
        Assertions.assertEquals(true, response.isStatusHas());
    }
}
