package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import org.assertj.core.api.Assertions;
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
public class FindAllTopicServiceTest {

    @Autowired private FindAllTopicService findAllTopicService;
    @Autowired private TopicRepository topicRepository;
    @Autowired private StudyPlanRepository studyPlanRepository;

    @BeforeEach
    public void setupInit(){
        StudyPlan studyPlan = new StudyPlan(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        Topic topic1 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Topic topic2 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        Topic topic3 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        Topic topic4 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"));
        Topic topic5 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"));
        topic1.setStudyPlan(studyPlan);
        topic2.setStudyPlan(studyPlan);
        topic3.setStudyPlan(studyPlan);
        topic4.setStudyPlan(studyPlan);
        topic5.setStudyPlan(studyPlan);
        topic1.setStatus(true);
        topic2.setStatus(true);
        topic3.setStatus(true);
        topic4.setStatus(true);
        topic5.setStatus(false);
        studyPlanRepository.save(studyPlan);
        topicRepository.saveAll(Arrays.asList(topic1, topic2, topic3, topic4, topic5));
    }

    @Test
    @DisplayName("Buscar todos os topicos por ID e retornar a quantidade salva no banco de dados")
    public void findAllTest(){
        Page<TopicResponse> topics = findAllTopicService.findAll(PageRequest.of(1, 5), UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        Assertions.assertThat(topics.getTotalElements()).isEqualTo(4L);
    }
}
