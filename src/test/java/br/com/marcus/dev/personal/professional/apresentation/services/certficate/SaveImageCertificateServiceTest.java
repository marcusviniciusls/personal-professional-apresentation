package br.com.marcus.dev.personal.professional.apresentation.services.certficate;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.SaveImageCertificateService;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveImageCertificateServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private SaveImageCertificateService saveImageCertificateService;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SendFileAwsS3 sendFileAwsS3;

    @Test
    @DisplayName("Atualizar uma certificacao com sucesso")
    public void saveImageCertificateTest() throws URISyntaxException {
        // Ambiente
        BranchActivity branchActivity = new BranchActivity("Papelaria");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("AXELOS", "URLIMAGE", branchActivity,
                "description");
        partner.setId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));
        partnerRepository.save(partner);
        Certificate certificate = new Certificate("NAME ATUALIZADO", "", partner);
        certificate.setId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        certificateRepository.save(certificate);
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);

        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(certificate);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));

        // Executar Teste
        saveImageCertificateService.saveImageCertificate(multipartFile, UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        Optional<Certificate> optionalCertificate = certificateRepository.findById(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        Assertions.assertEquals("caminho/imagem.png", optionalCertificate.get().getLogoImage());
    }

    @Test
    @DisplayName("Salvar uma certificacao e nao conseguir pois já tem um salvo")
    public void saveImageCertificateFileExceptionTest() throws URISyntaxException {
        // Ambiente
        BranchActivity branchActivity = new BranchActivity("Papelariaa");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("AXELOS", "URLIMAGE", branchActivity,
                "description");
        partner.setId(UUID.fromString("ab260da4-01fb-48f0-aec4-d7f9db2ff001"));
        partnerRepository.save(partner);
        Certificate certificate = new Certificate("NAME ATUALIZADO", "teste", partner);
        certificate.setId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        certificateRepository.save(certificate);
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);

        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(certificate);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));

        // Executar Teste
        Assertions.assertThrows(FileException.class, () -> saveImageCertificateService.saveImageCertificate(multipartFile, UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001")));
    }
}
