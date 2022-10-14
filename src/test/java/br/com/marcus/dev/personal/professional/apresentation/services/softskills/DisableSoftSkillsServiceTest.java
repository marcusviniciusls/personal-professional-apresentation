package br.com.marcus.dev.personal.professional.apresentation.services.softskills;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.DeleteSoftSkillsService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.DisableSoftSkillsService;
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
public class DisableSoftSkillsServiceTest {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private DisableSoftSkillsService disableSoftSkillsService;
    @Autowired private ActivitiesRepository activitiesRepository;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        SoftSkills softSkills = new SoftSkills("Boa comunicacao", true);
        softSkills.setId(id);
        softSkillsRepository.save(softSkills);

        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(softSkills);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Desabilitar SoftSkills")
    public void disableTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        disableSoftSkillsService.disable(id);
        // Testes Unitários
        SoftSkills softSkills = softSkillsRepository.findById(id).get();
        Assertions.assertFalse(softSkills.isStatusHas());
    }
}
