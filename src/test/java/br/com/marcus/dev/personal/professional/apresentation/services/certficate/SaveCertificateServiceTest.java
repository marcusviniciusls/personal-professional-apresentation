package br.com.marcus.dev.personal.professional.apresentation.services.certficate;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.SaveCertificateService;
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

import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveCertificateServiceTest {

    @Autowired private SaveCertificateService saveCertificateService;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SaveActivitiesService saveActivitiesService;

    @Test
    @DisplayName("Salvar uma certificacao com sucesso")
    public void saveTest(){
        // Dados de Entrada Solucao
        BranchActivity branchActivity = new BranchActivity("Tecnologia da Informacao");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("AXELOS", "URLIMAGE", branchActivity,
                "description");
        partner.setId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));
        partnerRepository.save(partner);
        CertificateSaveForm certificateSaveForm = new CertificateSaveForm();
        certificateSaveForm.setName("ITIL V4");
        certificateSaveForm.setPartnerId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));

        // Dados de Certificacao (Mockando Dados)
        Certificate certificate = new Certificate("Name", "logoImage", partner);
        Activities activities = new Activities(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff001"));
        activities.setCertificate(certificate);
        activities.setDate(LocalDate.now());
        activities.setDescription("Description");
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(certificate);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(saveActivitiesService.saveMovementCertificate(Mockito.any(Certificate.class))).willReturn(true);

        // Executando métodos
        CertificateResponse certificateResponse = saveCertificateService.save(certificateSaveForm);

        // Testes
        Assertions.assertTrue(certificateResponse != null);
        Assertions.assertEquals("Name", certificateResponse.getName());
        Assertions.assertEquals("logoImage", certificateResponse.getLogoImage());
        Assertions.assertTrue(certificateResponse.getPartnerResponse() != null);
    }
}
