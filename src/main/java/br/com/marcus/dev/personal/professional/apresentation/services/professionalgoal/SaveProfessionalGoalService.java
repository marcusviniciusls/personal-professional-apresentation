package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalGoalSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.factory.ProfessionalGoalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveProfessionalGoalService {

    @Autowired private ProfessionalGoalFactory professionalGoalFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ProfessionalGoalRepository professionalGoalRepository;

    public ProfessionalGoalResponse save(ProfessionalGoalSaveForm professionalGoalSaveForm){
        ProfessionalGoal professionalGoal = professionalGoalFactory.convertFormSaveInEntity(professionalGoalSaveForm);
        professionalGoal = (ProfessionalGoal) centerEntityService.setDataToSave(professionalGoal);
        professionalGoal = professionalGoalRepository.save(professionalGoal);
        ProfessionalGoalResponse professionalGoalResponse = professionalGoalFactory.convertEntityInResponse(professionalGoal);
        return professionalGoalResponse;
    }
}
