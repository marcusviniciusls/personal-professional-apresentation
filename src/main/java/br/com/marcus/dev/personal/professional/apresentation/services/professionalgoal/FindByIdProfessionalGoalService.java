package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.factory.ProfessionalGoalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdProfessionalGoalService {

    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ProfessionalGoalFactory professionalGoalFactory;

    public ProfessionalGoalResponse findById(UUID id){
        Optional<ProfessionalGoal> optionalProfessionalGoal = professionalGoalRepository.findById(id);
        if (optionalProfessionalGoal.isEmpty()){
            throw new ResourceNotFoundException("Professional Goal Not Found Exception");
        }
        ProfessionalGoal professionalGoal = optionalProfessionalGoal.get();
        if (!centerEntityService.isStatusSuperEntity(professionalGoal)){
            throw new ResourceNotFoundException("Professional Goal Not Found Exception");
        }
        ProfessionalGoalResponse professionalGoalResponse = professionalGoalFactory.convertEntityInResponse(professionalGoal);
        return professionalGoalResponse;
    }
}
