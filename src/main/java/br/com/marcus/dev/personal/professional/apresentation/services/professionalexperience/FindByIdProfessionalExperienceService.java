package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory.ProfessionalExperienceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdProfessionalExperienceService {

    @Autowired private ProfessionalExperienceRepository repository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ProfessionalExperienceFactory factory;

    public ProfessionalExperienceResponse findById(UUID id){
        Optional<ProfessionalExperience> optionalProfessionalExperience = repository.findById(id);
        if (optionalProfessionalExperience.isEmpty()){
            throw new ResourceNotFoundException("Professional Experience Not Found Exception");
        }
        ProfessionalExperience professionalExperience = optionalProfessionalExperience.get();
        if (!centerEntityService.isStatusSuperEntity(professionalExperience)){
            throw new ResourceNotFoundException("Professional Experience Not Found Exception");
        }
        return factory.convertEntityInResponse(professionalExperience);
    }
}
