package br.com.marcus.dev.personal.professional.apresentation.services.certficate.factory;

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
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
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

import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CertificateFactoryTest {

    @Autowired private CertificateFactory certificateFactory;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @DisplayName("Converter entidade em Resposta")
    public void convertEntityInResponseTest(){
        // Ambiente
        BranchActivity branchActivity = new BranchActivity("Papelariaa");
        Partner partner = new Partner("AXELOS", "URLIMAGE", branchActivity,
                "description");
        partner.setId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));
        Certificate certificate = new Certificate("NAME ATUALIZADO", "teste", partner);
        certificate.setId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));

        // Executando Método
        CertificateResponse resposta = certificateFactory.convertEntityInResponse(certificate);

        // Executando Testes
        Assertions.assertTrue(resposta != null);
        Assertions.assertEquals("NAME ATUALIZADO", resposta.getName());
        Assertions.assertEquals("teste", resposta.getLogoImage());
        Assertions.assertEquals(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"), resposta.getId());
        Assertions.assertTrue(resposta.getPartnerResponse() != null);
    }

    @Test
    @DisplayName("Converter SaveForm em Entidade")
    public void convertSaveFormInEntityTest(){
        // Ambiente
        CertificateSaveForm certificateSaveForm = new CertificateSaveForm();
        certificateSaveForm.setName("Name");
        certificateSaveForm.setPartnerId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));

        // Executando Método
        Certificate certificate = certificateFactory.convertSaveFormInEntity(certificateSaveForm);

        // Executando Testes
        Assertions.assertTrue(certificate != null);
        Assertions.assertEquals("Name", certificate.getName());
        Assertions.assertTrue(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001") != null);
    }

    @Test
    @DisplayName("Converter Update Form em Entidade")
    public void convertUpdateFormInEntityTest(){
        // Ambiente
        BranchActivity branchActivity = new BranchActivity("Papelariaaa");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("AXELOS", "URLIMAGE", branchActivity,
                "description");
        partner.setId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));
        Partner partner1 = new Partner("AXELOS", "URLIMAGE", branchActivity,
                "description");
        partner1.setId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff002"));
        partnerRepository.saveAll(Arrays.asList(partner1, partner));
        Certificate certificate1 = new Certificate("NAME", "teste", partner);
        certificate1.setId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        CertificateUpdateForm certificateUpdateForm = new CertificateUpdateForm();
        certificateUpdateForm.setName("NAME ATUALIZADO");
        certificateUpdateForm.setPartnerId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff002"));
        certificateRepository.saveAll(Arrays.asList(certificate1));
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);

        // Executando Método
        Certificate certificate = certificateFactory.convertUpdateFormInEntity(certificateUpdateForm, certificate1);

        Assertions.assertTrue(certificate != null);
        Assertions.assertEquals("NAME ATUALIZADO", certificate.getName());
        Assertions.assertEquals(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"), certificate.getId());
        Assertions.assertTrue(certificate.getPartner() != null);
        Assertions.assertEquals(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff002"), certificate.getPartner().getId());
    }
}
