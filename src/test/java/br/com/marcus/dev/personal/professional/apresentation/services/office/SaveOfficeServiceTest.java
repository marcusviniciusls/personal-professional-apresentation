package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
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
public class SaveOfficeServiceTest {

    @MockBean private CenterEntityService centerEntityService;
    @Autowired private SaveOfficeService saveOfficeService;

    @Test
    @Transactional
    @DisplayName("Salvar Office")
    public void saveTest(){
        // Dados Mockados
        Office office = new Office("Desenvolvedor Java", "teste", OfficeLevel.JUNIOR);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(office);
        // Preparacao
        OfficeSaveForm officeSaveForm = new OfficeSaveForm("Desenvolvedor Júnior", "teste", 1);
        // Executando método
        OfficeResponse officeResponse = saveOfficeService.save(officeSaveForm);
        // Teste Unitário
        Assertions.assertTrue(officeResponse != null);
        Assertions.assertEquals("Desenvolvedor Java", officeResponse.getName());
        Assertions.assertEquals("teste", officeResponse.getDescription());
        Assertions.assertEquals(OfficeLevel.JUNIOR, officeResponse.getOfficeLevel());
    }
}
