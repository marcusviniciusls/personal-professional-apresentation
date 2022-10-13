package br.com.marcus.dev.personal.professional.apresentation.services.office.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
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
public class OfficeFactoryTest {

    @Autowired private OfficeFactory officeFactory;

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Resposta (Office)")
    public void convertEntityInResponseTest(){
        Office office = new Office("Desenvolvedor Java", "description", OfficeLevel.SENIOR);
        OfficeResponse officeResponse = officeFactory.convertEntityInResponse(office);
        Assertions.assertTrue(officeResponse != null);
        Assertions.assertEquals("Desenvolvedor Java", officeResponse.getName());
        Assertions.assertEquals("description", officeResponse.getDescription());
        Assertions.assertEquals(OfficeLevel.SENIOR, officeResponse.getOfficeLevel());
    }

    @Test
    @Transactional
    @DisplayName("Converter SaveForm em Entidade (Office)")
    public void convertSaveFormInEntityTest(){
        OfficeSaveForm officeSaveForm = new OfficeSaveForm("Desenvolvedor Java", "description", 1);
        Office office = officeFactory.convertSaveFormInEntity(officeSaveForm);
        Assertions.assertTrue(office != null);
        Assertions.assertEquals("Desenvolvedor Java", office.getName());
        Assertions.assertEquals("description", office.getDescription());
        Assertions.assertEquals(OfficeLevel.JUNIOR, office.getOfficeLevel());
    }

    @Test
    @Transactional
    @DisplayName("Converter UpdateForm em Entidade (Office)")
    public void convertUpdateFormInEntityTest(){
        Office office = new Office("Desenvolvedor Java", "description", OfficeLevel.SENIOR);
        OfficeUpdateForm officeUpdateForm = new OfficeUpdateForm("Desenvolvedor Java teste", "description teste", 1);
        office = officeFactory.convertUpdateFormInEntity(officeUpdateForm, office);
        Assertions.assertTrue(office != null);
        Assertions.assertEquals("Desenvolvedor Java teste", office.getName());
        Assertions.assertEquals("description teste", office.getDescription());
        Assertions.assertEquals(OfficeLevel.JUNIOR, office.getOfficeLevel());
    }
}
