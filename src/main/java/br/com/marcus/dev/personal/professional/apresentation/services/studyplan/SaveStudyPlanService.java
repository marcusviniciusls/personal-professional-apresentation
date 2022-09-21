package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.StudyPlanSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.StudyPlanResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TopicRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.factory.StudyPlanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveStudyPlanService {

    @Autowired private StudyPlanFactory studyPlanFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private TopicRepository topicRepository;

    public StudyPlanResponse save(StudyPlanSaveForm studyPlanSaveForm){
        StudyPlan studyPlan = studyPlanFactory.convertSaveFormInEntity(studyPlanSaveForm);
        studyPlan = (StudyPlan) centerEntityService.setDataToSave(studyPlan);
        studyPlan = studyPlanRepository.save(studyPlan);
        for (Topic topic : studyPlan.getListTopic()){
            topic.setStudyPlan(studyPlan);
            topicRepository.save(topic);
        }
        return studyPlanFactory.convertEntityInResponse(studyPlan);
    }
}
