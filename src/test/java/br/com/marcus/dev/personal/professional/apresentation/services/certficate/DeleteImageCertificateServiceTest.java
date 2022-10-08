package br.com.marcus.dev.personal.professional.apresentation.services.certficate;

import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.DeleteImageCertificateService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteImageCertificateServiceTest {

    @Autowired private DeleteImageCertificateService deleteImageCertificateService;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private CertificateRepository certificateRepository;
    @MockBean private DeleteFileService deleteFileService;

    @Test
    @DisplayName("Apagar um certificate com sucesso")
    public void deleteImageS3Test(){
        // Salvar Certificate no Banco de Dados
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff501");
        Certificate certificateMock = new Certificate(id, "AWS", "");
        Certificate certificate = new Certificate(id, "AWS", "teste/teste");
        certificateRepository.save(certificate);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(certificateMock);
        BDDMockito.given(deleteFileService.deleteObjectS3(Mockito.any(String.class))).willReturn(true);
        // Executar a operacao
        deleteImageCertificateService.deleteImageS3(id);
        // Buscar a Certificacao Atualizada
        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
        // Teste
        Assertions.assertEquals("", optionalCertificate.get().getLogoImage());
    }
}
