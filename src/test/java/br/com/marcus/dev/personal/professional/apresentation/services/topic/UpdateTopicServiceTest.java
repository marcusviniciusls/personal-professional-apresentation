package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Profile;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
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

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateTopicServiceTest {

    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private TopicRepository topicRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private UpdateTopicService updateTopicService;

    @BeforeEach
    public void setupInit() {
        // Study Plan
        StudyPlan studyPlan = new StudyPlan(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        studyPlan.setStatus(true);
        studyPlanRepository.save(studyPlan);
        // Topic
        Topic topic1 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        topic1.setName("teste");
        topic1.setStatus(true);
        topicRepository.save(topic1);

        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(topic1);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar o Topic")
    public void updateTest(){
        // Executando o método
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701");
        TopicUpdateForm topicUpdateForm = new TopicUpdateForm();
        topicUpdateForm.setName("Teste");
        updateTopicService.update(id, topicUpdateForm);
        // Teste Unitário
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        Assertions.assertEquals("teste", optionalTopic.get().getName());
        Assertions.assertTrue(optionalTopic.isPresent());
    }
}
