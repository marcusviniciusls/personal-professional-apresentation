package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
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
public class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setupInit(){
        Project project1 = new Project(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Project project2 = new Project(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        project2.setStatus(false);
        testEntityManager.persist(project1);
        testEntityManager.persist(project2);
    }

    @Test
    @DisplayName("Deve conter pelo menos um Project")
    public void findAllTest(){
        Page<Project> page = projectRepository.findAll(PageRequest.of(1,1));
        Assertions.assertThat(page.getSize() == 1).isEqualTo(true);
    }
}
