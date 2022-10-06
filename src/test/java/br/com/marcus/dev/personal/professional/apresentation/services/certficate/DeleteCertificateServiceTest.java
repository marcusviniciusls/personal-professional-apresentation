package br.com.marcus.dev.personal.professional.apresentation.services.certficate;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.DeleteCertificateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteCertificateServiceTest {

    @Autowired private CertificateRepository certificateRepository;
    @Autowired private DeleteCertificateService deleteCertificateService;
    @Autowired private ActivitiesRepository activitiesRepository;

    @BeforeEach
    public void setupInit(){
        Certificate certificate = new Certificate();
        certificate.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        activities.setCertificate(certificate);
        activities.setStatus(true);
        certificateRepository.save(certificate);
        activitiesRepository.save(activities);
    }

    @Test
    @DisplayName("Apagar um certificate com sucesso")
    public void deleteTest(){
        UUID uuid = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701");
        deleteCertificateService.delete(uuid);
        Optional<Certificate> optionalCertificate = certificateRepository.findById(uuid);
        Assertions.assertFalse(optionalCertificate.isPresent());
    }
}
