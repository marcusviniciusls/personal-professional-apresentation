package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory.ProfessionalExperienceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateProfessionalExperienceService {

    @Autowired private FindByIdProfessionalExperienceService findByIdProfessionalExperienceService;
    @Autowired private ProfessionalExperienceFactory professionalExperienceFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;

    public void update(UUID id, ProfessionalExperienceFormUpdate professionalExperienceFormUpdate){
        ProfessionalExperience entity = findByIdProfessionalExperienceService.findByIdEntity(id);
        entity = professionalExperienceFactory.convertUpdateFormInEntity(entity, professionalExperienceFormUpdate);
        entity = (ProfessionalExperience) centerEntityService.setDataToUpdate(entity);
        professionalExperienceRepository.save(entity);
    }
}
