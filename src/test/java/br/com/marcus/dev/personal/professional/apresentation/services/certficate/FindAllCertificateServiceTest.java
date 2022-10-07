package br.com.marcus.dev.personal.professional.apresentation.services.certficate;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindAllCertificateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllCertificateServiceTest {

    @Autowired private FindAllCertificateService findAllCertificateService;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;

    @BeforeEach
    public void setupInit(){
        BranchActivity branchActivity = new BranchActivity("Branch Activity");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"), "name", ""
                , branchActivity, "description");
        partnerRepository.save(partner);
        Certificate certificate1 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff601"));
        Certificate certificate2 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff602"));
        Certificate certificate3 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff603"));
        Certificate certificate4 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff604"));
        Certificate certificate5 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff605"));
        certificate1.setPartner(partner);
        certificate2.setPartner(partner);
        certificate3.setPartner(partner);
        certificate4.setPartner(partner);
        certificate5.setPartner(partner);
        certificateRepository.saveAll(Arrays.asList(certificate1, certificate2, certificate3, certificate4, certificate5));
    }

    @Test
    @DisplayName("Trazer todas as certificacoes")
    public void findAllTest(){
        Page<CertificateResponse> test = findAllCertificateService.findAll(PageRequest.of(1, 5));
        Assertions.assertEquals(5, test.getSize());
    }
}
