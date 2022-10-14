package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
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
public class SavePartnerIdBranchActivityServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private SavePartnerIdBranchActivityService savePartnerIdBranchActivityService;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        BranchActivity branchActivity = new BranchActivity("Tecnologia de TI");
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        branchActivity.setId(id);
        branchActivityRepository.save(branchActivity);
        // Dados Mockados
        Partner partner = new Partner("name", "teste/teste", branchActivity, "description");
        partnerRepository.save(partner);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(partner);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Salvar novo Partner com um BranchActivity já cadastrado no Banco de Dados")
    public void saveTest() {
        // Execucao do método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        PartnerRequestFormSave partnerRequestFormSave = new PartnerRequestFormSave("name", "description");
        PartnerResponse response = savePartnerIdBranchActivityService.save(partnerRequestFormSave, id);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertTrue(response.getBranchActivityResponse() != null);
        Assertions.assertEquals("name", response.getName());
        Assertions.assertEquals("description", response.getDescription());
        Assertions.assertEquals("Tecnologia de TI", response.getBranchActivityResponse().getName());
    }
}
