package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory.ProfessionalExperienceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllProfessionalExperienceService {

    @Autowired private ProfessionalExperienceRepository repository;
    @Autowired private ProfessionalExperienceFactory factory;

    public Page<ProfessionalExperienceResponse> findAll(Pageable pageable){
        Page<ProfessionalExperience> pageEntity = repository.findAll(pageable);
        Page<ProfessionalExperienceResponse> response = pageEntity.map(pe -> factory.convertEntityInResponse(pe));
        return response;
    }
}
