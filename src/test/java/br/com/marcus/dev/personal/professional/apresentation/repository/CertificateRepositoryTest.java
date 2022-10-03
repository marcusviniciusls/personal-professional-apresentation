package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CertificateRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CertificateRepository certificateRepository;

    @BeforeEach
    public void setupInit(){
        Certificate certificate1 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Certificate certificate2 = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        certificate2.setStatus(false);
        testEntityManager.persist(certificate1);
        testEntityManager.persist(certificate2);
    }

    @Test
    @DisplayName("Deve conter pelo menos um Certificate")
    public void findAllTest(){
        Page<Certificate> page = certificateRepository.findAll(PageRequest.of(1,1));
        Assertions.assertThat(page.getSize() == 1).isEqualTo(true);
    }
}
