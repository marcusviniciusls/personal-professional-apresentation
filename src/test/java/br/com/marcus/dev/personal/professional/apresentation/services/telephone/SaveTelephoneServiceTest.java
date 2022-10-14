package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
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
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveTelephoneServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private SaveTelephoneService saveTelephoneService;

    @BeforeEach
    public void setupInit(){
        DataPersonal dataPersonal = new DataPersonal("Marcus", 27, LocalDate.of(1995,4,20),
                MaritalStatus.MARRIED);
        dataPersonal.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        dataPersonalRepository.save(dataPersonal);

        Telephone telephone = new Telephone("55", "11", "993527709", dataPersonal);

        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(telephone);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Salvar Telephone")
    public void saveTest(){
        String id = "cb260da4-01fb-48f0-aec4-d7f9db2ff371";
        TelephoneFormSave telephoneFormSave = new TelephoneFormSave("55", "11", "993527709", id);
        TelephoneDto telephoneDto = saveTelephoneService.save(telephoneFormSave);

        Assertions.assertTrue(telephoneDto != null);
        Assertions.assertEquals("55", telephoneDto.getDdi());
        Assertions.assertEquals("11", telephoneDto.getDdd());
        Assertions.assertEquals("993527709", telephoneDto.getNumber());
    }
}
