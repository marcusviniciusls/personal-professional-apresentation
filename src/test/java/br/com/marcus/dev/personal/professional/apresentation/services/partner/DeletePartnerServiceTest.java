package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
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
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeletePartnerServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private DeletePartnerService deletePartnerService;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        BranchActivity branchActivity = new BranchActivity("Tecno3");
        branchActivityRepository.save(branchActivity);
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Partner partner = new Partner(id, "TI ROX", "teste/teste", branchActivity, "");
        partnerRepository.save(partner);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Partner")
    public void deleteTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deletePartnerService.delete(id);
        // Teste Unitário
        Optional<Partner> optionalPartner = partnerRepository.findById(id);
        Assertions.assertTrue(optionalPartner.isEmpty());
    }
}
