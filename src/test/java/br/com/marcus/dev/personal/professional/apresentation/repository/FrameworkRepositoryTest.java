package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
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
public class FrameworkRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private FrameworkRepository frameworkRepository;

    @BeforeEach
    public void setupInit(){
        Framework framework1 = new Framework(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Framework framework2 = new Framework(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        framework2.setStatus(false);
        testEntityManager.persist(framework1);
        testEntityManager.persist(framework2);
    }

    @Test
    @DisplayName("Deve conter pelo menos um Framework")
    public void findAllTest(){
        Page<Framework> page = frameworkRepository.findAll(PageRequest.of(1,1));
        Assertions.assertThat(page.getSize() == 1).isEqualTo(true);
    }
}
