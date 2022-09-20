package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.factory.TopicFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTopicService {

    @Autowired private FindByIdTopicService findByIdTopicService;
    @Autowired private TopicFactory topicFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private TopicRepository topicRepository;

    public void update(UUID id, TopicUpdateForm topicUpdateForm){
        Topic topic = findByIdTopicService.findByIdEntity(id);
        topic = topicFactory.convertUpdateFormInEntity(topic, topicUpdateForm);
        topic = (Topic) centerEntityService.setDataToUpdate(topic);
        topicRepository.save(topic);
    }
}
