package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdTopicServiceTest {

    @Autowired private FindByIdTopicService findByIdTopicService;
    @Autowired private TopicRepository topicRepository;

    @BeforeEach
    public void setupInit(){
        Topic topic1 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Topic topic2 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        topic1.setStatus(true);
        topic2.setStatus(false);
        topicRepository.save(topic1);
        topicRepository.save(topic2);
    }

    @Test
    @DisplayName("Buscar Topic por Id com sucesso")
    public void findByIdTest(){
        TopicResponse topicResponse = findByIdTopicService.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(topicResponse != null).isTrue();
    }

    @Test
    @DisplayName("Buscar Topic por Id e nao encontrar")
    public void findByIdNotFoundTest(){
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> findByIdTopicService.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703")))
                .withMessage("Topic Not Found Exception");
    }

    @Test
    @DisplayName("Buscar Topic por Id e nao encontrar ")
    public void findByIdNotFoundStatusTest(){
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> findByIdTopicService.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702")))
                .withMessage("Topic Not Found Exception");
    }

    @Test
    @DisplayName("Buscar Topic por Id com sucesso")
    public void findByIdEntityTest(){
        Topic topic = findByIdTopicService.findByIdEntity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(topic != null).isTrue();
    }

    @Test
    @DisplayName("Buscar Topic por Id e nao encontrar")
    public void findByIdEntityNotFoundTest(){
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> findByIdTopicService.findByIdEntity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703")))
                .withMessage("Topic Not Found Exception");
    }

    @Test
    @DisplayName("Buscar Topic por Id e nao encontrar ")
    public void findByIdEntityNotFoundStatusTest(){
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> findByIdTopicService.findByIdEntity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702")))
                .withMessage("Topic Not Found Exception");
    }
}
