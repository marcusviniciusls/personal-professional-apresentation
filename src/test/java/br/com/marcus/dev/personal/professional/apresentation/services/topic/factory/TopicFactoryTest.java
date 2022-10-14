package br.com.marcus.dev.personal.professional.apresentation.services.topic.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TopicFactoryTest {

    @Autowired private TopicFactory topicFactory;
    @Autowired private StudyPlanRepository studyPlanRepository;

    @BeforeEach
    public void setupInit(){
        StudyPlan studyPlan = new StudyPlan(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        studyPlanRepository.save(studyPlan);
    }

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Resposta (Topic)")
    public void convertEntityInResponseTest(){
        Topic topic = new Topic("Teste");
        TopicResponse topicResponse = topicFactory.convertEntityInResponse(topic);
        Assertions.assertTrue(topicResponse != null);
        Assertions.assertEquals("Teste", topicResponse.getName());
    }

    @Test
    @Transactional
    @DisplayName("Converter SaveForm em Entidade (Topic)")
    public void convertSaveFormInEntityTest(){
        TopicSaveForm topicSaveForm = new TopicSaveForm("Teste");
        Topic topic = topicFactory.convertSaveFormInEntity(topicSaveForm);
        Assertions.assertTrue(topic != null);
        Assertions.assertEquals("Teste", topic.getName());
    }

    @Test
    @Transactional
    @DisplayName("Converter UpdateForm em Entidade (Topic)")
    public void convertUpdateFormInEntityTest(){
        TopicUpdateForm topicUpdateForm = new TopicUpdateForm("teste", UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        Topic topic = new Topic("Teste");
        StudyPlan studyPlan = new StudyPlan(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff106"));
        topic.setStudyPlan(studyPlan);
        topic = topicFactory.convertUpdateFormInEntity(topic, topicUpdateForm);
        Assertions.assertTrue(topic != null);
        Assertions.assertEquals("teste", topic.getName());
        Assertions.assertTrue(topic.getStudyPlan() != null);
    }
}
