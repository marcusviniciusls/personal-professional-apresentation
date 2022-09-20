package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTopicService {

    @Autowired private FindByIdTopicService findByIdTopicService;
    @Autowired private TopicRepository topicRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Topic topic = findByIdTopicService.findByIdEntity(id);
        try {
            topicRepository.delete(topic);
        } catch(DataIntegrityViolationException ex){
            topic = (Topic) centerEntityService.setDataToDelete(topic);
            topicRepository.save(topic);
        }
    }
}
