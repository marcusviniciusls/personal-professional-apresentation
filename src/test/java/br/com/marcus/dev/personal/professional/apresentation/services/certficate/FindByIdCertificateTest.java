package br.com.marcus.dev.personal.professional.apresentation.services.certficate;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindByIdCertificateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdCertificateTest {

    @Autowired private CertificateRepository certificateRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private FindByIdCertificateService findByIdCertificateService;

    @BeforeEach
    public void setupInit(){
        BranchActivity branchActivity = new BranchActivity("Branch Activity 1234567");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff801"), "name09876", ""
                , branchActivity, "description");
        partnerRepository.save(partner);
        Certificate certificate1 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff501"));
        certificate1.setPartner(partner);
        Certificate certificate2 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff502"));
        certificate2.setStatus(false);
        certificate2.setPartner(partner);
        certificateRepository.saveAll(Arrays.asList(certificate1, certificate2));
    }

    @Test
    @DisplayName("Buscar Certificacao com sucesso")
    public void findByIdTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff501");
        CertificateResponse certificateResponse = findByIdCertificateService.findById(id);
        Assertions.assertTrue(certificateResponse != null);
    }

    @Test
    @DisplayName("Buscar Certificacao e retornar not found Status false")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff502");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCertificateService.findById(id));
    }

    @Test
    @DisplayName("Buscar Certificacao e retornar Resource Not Found Exception")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff503");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCertificateService.findById(id));
    }

    @Test
    @DisplayName("Buscar Certificacao com sucesso")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff501");
        Certificate certificate = findByIdCertificateService.findByIdEntity(id);
        Assertions.assertTrue(certificate != null);
    }

    @Test
    @DisplayName("Buscar Certificacao e retornar not found Status false")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff502");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCertificateService.findByIdEntity(id));
    }

    @Test
    @DisplayName("Buscar Certificacao e retornar Resource Not Found Exception")
    public void findByIdEntitydNotFoundTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff503");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCertificateService.findByIdEntity(id));
    }
}
