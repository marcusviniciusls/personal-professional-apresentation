package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalGoalUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.factory.ProfessionalGoalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateProfessionalGoalService {

    @Autowired private FindByIdProfessionalGoalService findByIdProfessionalGoalService;
    @Autowired private ProfessionalGoalFactory professionalGoalFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ProfessionalGoalRepository professionalGoalRepository;

    public ProfessionalGoalResponse update(UUID id, ProfessionalGoalUpdateForm professionalGoalUpdateForm){
        ProfessionalGoal professionalGoal = findByIdProfessionalGoalService.findByIdEntity(id);
        professionalGoal = professionalGoalFactory.convertFormUpdateInEntity(professionalGoalUpdateForm, professionalGoal);
        professionalGoal = (ProfessionalGoal) centerEntityService.setDataToUpdate(professionalGoal);
        professionalGoal = professionalGoalRepository.save(professionalGoal);
        ProfessionalGoalResponse professionalGoalResponse = professionalGoalFactory.convertEntityInResponse(professionalGoal);
        return professionalGoalResponse;
    }
}
