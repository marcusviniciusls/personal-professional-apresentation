package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.DeleteActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProfessionalExperienceService {

    @Autowired private FindByIdProfessionalExperienceService findByIdProfessionalExperienceService;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DeleteActivitiesService deleteActivitiesService;

    public void delete(UUID id){
        ProfessionalExperience entity = findByIdProfessionalExperienceService.findByIdEntity(id);
        deleteActivitiesService.deleteMovementProfessionalExperience(entity.getId());
        try {
            professionalExperienceRepository.delete(entity);
        } catch(DataIntegrityViolationException ex){
            entity = (ProfessionalExperience) centerEntityService.setDataToDelete(entity);
            professionalExperienceRepository.save(entity);
        }
    }
}
