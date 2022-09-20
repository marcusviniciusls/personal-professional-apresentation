package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.factory.TopicFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindAllTopicService {

    @Autowired private TopicRepository topicRepository;
    @Autowired private TopicFactory topicFactory;

    public Page<TopicResponse> findAll(Pageable pageable, UUID idStudyPlan){
        Page<Topic> pageTopic = topicRepository.findAll(pageable, idStudyPlan);
        Page<TopicResponse> pageTopicResponse = pageTopic.map(topic -> topicFactory.convertEntityInResponse(topic));
        return pageTopicResponse;
    }
}
