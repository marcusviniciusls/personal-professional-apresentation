package br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TelephoneFactoryTest {

    @Autowired private TelephoneFactory telephoneFactory;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private DataPersonalRepository dataPersonalRepository;

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Dto (Telephone)")
    public void convertEntityInDtoTest(){
        Telephone telephone = new Telephone("55", "11", "993527709");
        TelephoneDto telephoneDto = telephoneFactory.convertEntityInDto(telephone);
        Assertions.assertTrue(telephoneDto != null);
        Assertions.assertEquals("55", telephoneDto.getDdi());
        Assertions.assertEquals("11", telephoneDto.getDdd());
        Assertions.assertEquals("993527709", telephoneDto.getNumber());
    }

    @Test
    @Transactional
    @DisplayName("Converter Dto em Lista de Entidade (Telephone)")
    public void convertDtoInEntityListSaveTest(){
        DataPersonal dataPersonal = new DataPersonal("Marcus", 27, LocalDate.of(1995,4,20),
                MaritalStatus.MARRIED);
        dataPersonalRepository.save(dataPersonal);
        Telephone telephoneMockado = new Telephone("55", "11", "993527709");

        TelephoneForm telephone1 = new TelephoneForm("55", "11", "993527709");
        TelephoneForm telephone2 = new TelephoneForm("55", "11", "993527709");
        TelephoneForm telephone3 = new TelephoneForm("55", "11", "993527709");
        TelephoneForm telephone4 = new TelephoneForm("55", "11", "993527709");
        TelephoneForm telephone5 = new TelephoneForm("55", "11", "993527709");
        List<TelephoneForm> listTelephone = new ArrayList<>();
        listTelephone.add(telephone1);
        listTelephone.add(telephone2);
        listTelephone.add(telephone3);
        listTelephone.add(telephone4);
        listTelephone.add(telephone5);

        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(telephoneMockado);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);

        List<Telephone> list = telephoneFactory.convertDtoInEntityListSave(listTelephone, dataPersonal);
        Assertions.assertEquals(5, list.size());
    }

    @Test
    @Transactional
    @DisplayName("Converter FormSave em Entidade (Telephone)")
    public void convertFormInEntitySaveTest(){
        TelephoneFormSave telephoneFormSave = new TelephoneFormSave("55", "11", "993527709", "");
        Telephone telephone = telephoneFactory.convertFormInEntitySave(telephoneFormSave);
        Assertions.assertTrue(telephone != null);
        Assertions.assertEquals("55", telephone.getDdi());
        Assertions.assertEquals("11", telephone.getDdd());
        Assertions.assertEquals("993527709", telephone.getNumber());
    }

    @Test
    @Transactional
    @DisplayName("Converter FormUpdate em Entidade (Telephone)")
    public void convertFormInEntityUpdateTest(){
        TelephoneFormUpdate telephoneFormUpdate = new TelephoneFormUpdate("55", "11", "993527709");
        Telephone telephone = new Telephone("55", "11", "993527709");
        telephone = telephoneFactory.convertFormInEntityUpdate(telephone, telephoneFormUpdate);
        Assertions.assertTrue(telephone != null);
        Assertions.assertEquals("55", telephone.getDdi());
        Assertions.assertEquals("11", telephone.getDdd());
        Assertions.assertEquals("993527709", telephone.getNumber());
    }
}
