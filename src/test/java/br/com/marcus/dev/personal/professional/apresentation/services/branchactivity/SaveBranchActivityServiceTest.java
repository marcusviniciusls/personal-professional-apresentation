package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveBranchActivityServiceTest {

    @Autowired private SaveBranchActivityService saveBranchActivityService;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @DisplayName("Salvar um Ramo de Atividade e retornar um Response")
    public void saveTest(){
        BranchActivity branchActivity = new BranchActivity("Tecnologia01");
        BranchActivityForm branchActivityForm = new BranchActivityForm("Tecnologia");
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(branchActivity);
        BranchActivityResponse response = saveBranchActivityService.save(branchActivityForm);
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Tecnologia01", response.getName());
    }

    @Test
    @DisplayName("Salvar um Ramo de Atividade e retornar uma Entidade")
    public void saveEntityTest(){
        BranchActivity branchActivity = new BranchActivity("Tecnologia02");
        BranchActivityForm branchActivityForm = new BranchActivityForm("Tecnologia02");
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(branchActivity);
        branchActivity = saveBranchActivityService.saveEntity(branchActivityForm);
        Assertions.assertTrue(branchActivity != null);
        Assertions.assertEquals("Tecnologia02", branchActivity.getName());
    }
}
