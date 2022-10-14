package br.com.marcus.dev.personal.professional.apresentation.services.partner.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFullFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PartnerFactoryTest {

    @Autowired private PartnerFactory partnerFactory;
    @Autowired private BranchActivityRepository branchActivityRepository;

    @Test
    @DisplayName("Converter Entidade em Dto (Partner)")
    public void convertEntityInDtoTest() {
        BranchActivity branchActivity = new BranchActivity("Tecno10");
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Partner partner = new Partner(id, "TI ROX", "teste/teste", branchActivity, "");
        PartnerResponse partnerResponse = partnerFactory.convertEntityInDto(partner);
        Assertions.assertTrue(partnerResponse != null);
        Assertions.assertEquals("TI ROX", partnerResponse.getName());
        Assertions.assertEquals("teste/teste", partnerResponse.getUrlImage());
        Assertions.assertEquals("", partnerResponse.getDescription());
        Assertions.assertEquals(id, partnerResponse.getId());
    }

    @Test
    @DisplayName("Converter RequestFullForm em Entidade (Partner)")
    public void convertRequestFullFormInEntityTest() {
        PartnerRequestFullFormSave partnerRequestFormSave = new PartnerRequestFullFormSave("name", "branchActivity","description");
        Partner partner = partnerFactory.convertRequestFullFormInEntity(partnerRequestFormSave);
        Assertions.assertTrue(partner != null);
        Assertions.assertEquals("name", partner.getName());
        Assertions.assertEquals("description", partner.getDescription());
    }

    @Test
    @DisplayName("Converter RequestForm em Entidade (Partner)")
    public void convertRequestFormInEntityTest() {
        PartnerRequestFormSave partnerRequestFormSave = new PartnerRequestFormSave("name","description");
        Partner partner = partnerFactory.convertRequestFormInEntity(partnerRequestFormSave);
        Assertions.assertTrue(partner != null);
        Assertions.assertEquals("name", partner.getName());
        Assertions.assertEquals("description", partner.getDescription());
    }

    @Test
    @DisplayName("Converter UpdateForm em Entidade (Partner)")
    public void convertUpdateFormInEntityTest() {
        BranchActivity branchActivity = new BranchActivity("Tecno092");
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        branchActivity.setId(id);
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("TI ROX", "teste/teste", "teste");
        partner.setBranchActivity(branchActivity);
        PartnerRequestFormUpdate partnerRequestFormUpdate = new PartnerRequestFormUpdate("name","description", UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        partner = partnerFactory.convertUpdateFormInEntity(partnerRequestFormUpdate, partner);
        Assertions.assertTrue(partner != null);
        Assertions.assertEquals("name", partner.getName());
        Assertions.assertEquals("description", partner.getDescription());
    }
}
