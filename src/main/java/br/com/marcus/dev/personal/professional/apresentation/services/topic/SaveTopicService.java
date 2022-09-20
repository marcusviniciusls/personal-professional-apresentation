package br.com.marcus.dev.personal.professional.apresentation.services.topic;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.FindByIdStudyPlanService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.factory.TopicFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveTopicService {

    @Autowired private FindByIdStudyPlanService findByIdStudyPlanService;
    @Autowired private TopicFactory topicFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private TopicRepository topicRepository;

    public TopicResponse save(TopicSaveForm topicSaveForm){
        StudyPlan studyPlan = findByIdStudyPlanService.findByIdEntity(topicSaveForm.getStudyPlanId());
        Topic topic = topicFactory.convertSaveFormInEntity(topicSaveForm);
        topic.setStudyPlan(studyPlan);
        topic = (Topic) centerEntityService.setDataToSave(topic);
        topic = topicRepository.save(topic);
        return topicFactory.convertEntityInResponse(topic);
    }
}
