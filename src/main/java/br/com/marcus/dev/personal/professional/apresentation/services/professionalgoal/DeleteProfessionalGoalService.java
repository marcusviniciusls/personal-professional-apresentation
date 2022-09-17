package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.DeleteActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProfessionalGoalService {

    @Autowired private FindByIdProfessionalGoalService findByIdProfessionalGoalService;
    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DeleteActivitiesService deleteActivitiesService;

    public void delete(UUID id){
        ProfessionalGoal professionalGoal = findByIdProfessionalGoalService.findByIdEntity(id);
        deleteActivitiesService.deleteMovementProfessionalGoal(professionalGoal.getId());
        try{
            professionalGoalRepository.delete(professionalGoal);
        } catch(DataIntegrityViolationException ex){
            professionalGoal = (ProfessionalGoal) centerEntityService.setDataToDelete(professionalGoal);
            professionalGoalRepository.save(professionalGoal);
        }
    }
}
