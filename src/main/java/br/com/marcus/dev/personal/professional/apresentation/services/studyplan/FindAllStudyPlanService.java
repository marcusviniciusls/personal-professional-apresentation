package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.StudyPlanResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.StudyPlan;
import br.com.marcus.dev.personal.professional.apresentation.repository.StudyPlanRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.factory.StudyPlanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllStudyPlanService {

    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private StudyPlanFactory studyPlanFactory;

    public Page<StudyPlanResponse> findAll(Pageable pageable){
        Page<StudyPlan> pageStudyPlan = studyPlanRepository.findAll(pageable);
        Page<StudyPlanResponse> pageStudyPlanResponse = pageStudyPlan.map(sp -> studyPlanFactory.convertEntityInResponse(sp));
        return pageStudyPlanResponse;
    }
}
