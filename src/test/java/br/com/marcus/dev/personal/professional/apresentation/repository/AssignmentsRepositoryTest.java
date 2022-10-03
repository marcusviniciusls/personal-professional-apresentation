package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AssignmentsRepositoryTest {

    @Autowired private TestEntityManager testEntityManager;
    @Autowired private AssignmentsRepository assignmentsRepository;

    @BeforeEach
    public void setupInit(){
        Assignments assignments1 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assignments assignments2 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        assignments2.setStatus(false);
        testEntityManager.persist(assignments1);
        testEntityManager.persist(assignments2);
    }

    @Test
    @DisplayName("Deve conter pelo menos um Assignments")
    public void findAllTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701");
        Pageable pageable = PageRequest.of(1,1);
        Page<Assignments> pageAssignments = assignmentsRepository.findAll(pageable, id);
        Assertions.assertThat(pageAssignments.getSize() == 1).isEqualTo(true);
    }
}
