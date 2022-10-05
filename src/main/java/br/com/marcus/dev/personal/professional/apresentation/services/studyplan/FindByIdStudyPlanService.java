package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdStudyPlanService {

    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private CenterEntityService centerEntityService;

    public StudyPlan findByIdEntity(UUID id){
        Optional<StudyPlan> optionalStudyPlan = studyPlanRepository.findById(id);
        if (optionalStudyPlan.isEmpty()){
            throw new ResourceNotFoundException("Study Plan Not Found Exception");
        }
        StudyPlan studyPlan = optionalStudyPlan.get();
        if (!centerEntityService.isStatusSuperEntity(studyPlan)){
            throw new ResourceNotFoundException("Study Plan Not Found Exception");
        }
        return studyPlan;
    }
}
