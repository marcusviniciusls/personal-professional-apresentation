package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
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
public class PartnerRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private PartnerRepository partnerRepository;

    @BeforeEach
    public void setupInit(){
        Partner partner1 = new Partner(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Partner partner2 = new Partner(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        partner2.setStatus(false);
        testEntityManager.persist(partner1);
        testEntityManager.persist(partner2);
    }

    @Test
    @DisplayName("Deve conter pelo menos um Partner")
    public void findAllTest(){
        Page<Partner> page = partnerRepository.findAll(PageRequest.of(1,1));
        Assertions.assertThat(page.getSize() == 1).isEqualTo(true);
    }
}
