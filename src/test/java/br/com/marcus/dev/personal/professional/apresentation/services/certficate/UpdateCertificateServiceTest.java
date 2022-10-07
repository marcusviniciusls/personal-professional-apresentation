package br.com.marcus.dev.personal.professional.apresentation.services.certficate;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.UpdateCertificateService;
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

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateCertificateServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private UpdateCertificateService updateCertificateService;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @DisplayName("Atualizar uma certificacao com sucesso")
    public void updateTest(){
        // Ambiente
        BranchActivity branchActivity = new BranchActivity("Tecnologia da Informacao (TI)");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("AXELOS", "URLIMAGE", branchActivity,
                "description");
        partner.setId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));
        partnerRepository.save(partner);
        Certificate certificate = new Certificate("NAME ATUALIZADO", "logoImage", partner);
        certificate.setId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        certificateRepository.save(certificate);

        // Dados de Entrada
        CertificateUpdateForm certificateUpdateForm = new CertificateUpdateForm();
        certificateUpdateForm.setName("NAME ATUALIZADO");
        certificateUpdateForm.setPartnerId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));
        UUID id = UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001");
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(certificate);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);

        // Rodar Método
        CertificateResponse certificateResponse = updateCertificateService.update(certificateUpdateForm, id);

        // Verificar Testes
        Assertions.assertTrue(certificate != null);
        Assertions.assertEquals("NAME ATUALIZADO", certificateResponse.getName());
        Assertions.assertEquals(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"), certificateResponse.getPartnerResponse().getId());
        Assertions.assertTrue(certificateResponse.getPartnerResponse() != null);
    }
}
