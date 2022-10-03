package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
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
public class TopicRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private TopicRepository topicRepository;

    @BeforeEach
    public void setupInit(){
        Topic topic1 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Topic topic2 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        StudyPlan studyPlan1 = new StudyPlan(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        topic1.setStudyPlan(studyPlan1);
        topic2.setStatus(false);
        testEntityManager.persist(studyPlan1);
        testEntityManager.persist(topic1);
        testEntityManager.persist(topic2);
    }

    @Test
    @DisplayName("Deve conter pelo menos um BranchActivity")
    public void findAllTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703");
        Page<Topic> page = topicRepository.findAll(PageRequest.of(1,1), id);
        Assertions.assertThat(page.getSize() == 1).isEqualTo(true);
    }
}
