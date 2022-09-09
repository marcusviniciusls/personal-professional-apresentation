package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.factory.ProfessionalGoalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllProfessionalGoalService {

    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private ProfessionalGoalFactory professionalGoalFactory;

    public Page<ProfessionalGoalResponse> findAll(Pageable pageable){
        Page<ProfessionalGoal> pageProfessionalGoal = professionalGoalRepository.findAll(pageable);
        Page<ProfessionalGoalResponse> pageProfessionalGoalResponse
                = pageProfessionalGoal.map(pg -> professionalGoalFactory.convertEntityInResponse(pg));
        return pageProfessionalGoalResponse;
    }
}
