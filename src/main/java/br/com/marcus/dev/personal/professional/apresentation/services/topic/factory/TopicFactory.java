package br.com.marcus.dev.personal.professional.apresentation.services.topic.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.FindByIdStudyPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private FindByIdStudyPlanService findByIdStudyPlanService;

    public TopicResponse convertEntityInResponse(Topic topic){
        return modelMapper.map(topic, TopicResponse.class);
    }

    public Topic convertSaveFormInEntity(TopicSaveForm topicSaveForm){
        return new Topic(topicSaveForm.getName());
    }

    public Topic convertUpdateFormInEntity(Topic topic, TopicUpdateForm topicUpdateForm){
        if (topicUpdateForm.getName() != null && !topicUpdateForm.getName().equals("")){
            topic.setName(topicUpdateForm.getName());
        }
        if (topicUpdateForm.getStudyPlanId() != null){
            if (!topic.getStudyPlan().getId().equals(topicUpdateForm.getStudyPlanId())){
                StudyPlan studyPlan = findByIdStudyPlanService.findByIdEntity(topicUpdateForm.getStudyPlanId());
                topic.setStudyPlan(studyPlan);
            }
        }
        return topic;
    }
}
