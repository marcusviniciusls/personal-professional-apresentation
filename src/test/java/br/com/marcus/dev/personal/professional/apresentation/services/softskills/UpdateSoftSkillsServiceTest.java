package br.com.marcus.dev.personal.professional.apresentation.services.softskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.UpdateSoftSkillsService;
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
public class UpdateSoftSkillsServiceTest {

    @MockBean private CenterEntityService centerEntityService;
    @Autowired private UpdateSoftSkillsService updateSoftSkillsService;
    @Autowired private SoftSkillsRepository softSkillsRepository;

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
    @DisplayName("Atualizar SoftSkills")
    public void updateTest(){
        // Preparacao do Teste
        SoftSkillsFormUpdate softSkillsFormUpdate = new SoftSkillsFormUpdate("Boa Comunicacao");
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        SoftSkillsResponse response = updateSoftSkillsService.update(softSkillsFormUpdate, id);
        // Teste Unitário
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Boa comunicacao", response.getName());
        Assertions.assertEquals(true, response.isStatusHas());
    }
}
