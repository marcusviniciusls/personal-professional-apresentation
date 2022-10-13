package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
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
public class UpdateOfficeServiceTest {

    @Autowired private OfficeRepository officeRepository;
    @Autowired private UpdateOfficeService updateOfficeService;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        // Dados Mockados
        Office office = new Office("Desenvolvedor Java", "teste", OfficeLevel.JUNIOR);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(office);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        office.setId(id);
        officeRepository.save(office);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Office")
    public void updateTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        OfficeUpdateForm officeUpdateForm = new OfficeUpdateForm("Desenvolvedor Java", "teste", 1);
        OfficeResponse officeResponse = updateOfficeService.update(id, officeUpdateForm);
        // Teste Unitário
        Assertions.assertTrue(officeResponse != null);
        Assertions.assertEquals("Desenvolvedor Java", officeResponse.getName());
        Assertions.assertEquals("teste", officeResponse.getDescription());
        Assertions.assertEquals(OfficeLevel.JUNIOR, officeResponse.getOfficeLevel());
    }
}
