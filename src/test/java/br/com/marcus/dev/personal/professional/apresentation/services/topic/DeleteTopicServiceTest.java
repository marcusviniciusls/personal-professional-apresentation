package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import org.assertj.core.api.Assertions;
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
public class DeleteTopicServiceTest {

    @Autowired private DeleteTopicService deleteTopicService;
    @Autowired private TopicRepository topicRepository;

    @BeforeEach
    public void setupInit(){
        Topic topic = new Topic();
        topic.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        topicRepository.save(topic);
    }

    @Test
    @DisplayName("Apagar um tópico com sucesso")
    public void deleteTest(){
        deleteTopicService.delete(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Topic> optionalTopic = topicRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(optionalTopic.isPresent()).isFalse();
    }
}
