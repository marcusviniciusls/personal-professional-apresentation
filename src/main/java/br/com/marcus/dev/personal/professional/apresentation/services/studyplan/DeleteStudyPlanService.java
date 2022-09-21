package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteStudyPlanService {

    @Autowired private FindByIdStudyPlanService findByIdStudyPlanService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private StudyPlanRepository studyPlanRepository;

    public void delete(UUID id){
        StudyPlan studyPlan = findByIdStudyPlanService.findByIdEntity(id);
        try {
            studyPlanRepository.delete(studyPlan);
        } catch(DataIntegrityViolationException ex){
            studyPlan = (StudyPlan) centerEntityService.setDataToDelete(studyPlan);
            studyPlanRepository.save(studyPlan);
        }
    }
}
