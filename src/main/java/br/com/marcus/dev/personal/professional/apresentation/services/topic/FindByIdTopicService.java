package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.factory.TopicFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdTopicService {

    @Autowired private TopicRepository topicRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private TopicFactory topicFactory;

    public TopicResponse findById(UUID id){
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()){
            throw new ResourceNotFoundException("Topic Not Found Exception");
        }
        Topic topic = optionalTopic.get();
        if (!centerEntityService.isStatusSuperEntity(topic)){
            throw new ResourceNotFoundException("Topic Not Found Exception");
        }
        return topicFactory.convertEntityInResponse(topic);
    }
}
