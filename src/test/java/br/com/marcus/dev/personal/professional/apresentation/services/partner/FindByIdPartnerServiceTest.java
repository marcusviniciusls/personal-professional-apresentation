package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdPartnerServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private FindByIdPartnerService findByIdPartnerService;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        BranchActivity branchActivity = new BranchActivity("Tecno1");
        branchActivityRepository.save(branchActivity);
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Partner partner = new Partner(id, "TI ROX", "teste/teste", branchActivity, "teste");
        partnerRepository.save(partner);

        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Partner partnerStatusFalse = new Partner(idStatusFalse, "TI ROX", "teste/teste", branchActivity, "teste");
        partnerStatusFalse.setStatus(false);
        partnerRepository.save(partnerStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id com sucesso")
    public void findByIdTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        PartnerResponse partnerResponse = findByIdPartnerService.findById(id);
        // Testes Unitários
        Assertions.assertTrue(partnerResponse != null);
        Assertions.assertEquals("TI ROX", partnerResponse.getName());
        Assertions.assertEquals("teste/teste", partnerResponse.getUrlImage());
        Assertions.assertEquals("teste", partnerResponse.getDescription());
        Assertions.assertEquals(id, partnerResponse.getId());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id com sucesso")
    public void findByIdPartnerTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Partner partner = findByIdPartnerService.findByIdPartner(id);
        // Testes Unitários
        Assertions.assertTrue(partner != null);
        Assertions.assertEquals("TI ROX", partner.getName());
        Assertions.assertEquals("teste/teste", partner.getUrlImage());
        Assertions.assertEquals("teste", partner.getDescription());
        Assertions.assertEquals(id, partner.getId());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id Status False")
    public void findByIdStatusFalseTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartnerService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id StatusFalse")
    public void findByIdPartnerStatusFalseTest(){
        // Testes Unitários
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartnerService.findByIdPartner(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id NotFound")
    public void findByIdNotFoundTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartnerService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id NotFound")
    public void findByIdPartnerNotFoundTest(){
        // Testes Unitários
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartnerService.findByIdPartner(id));
    }
}
