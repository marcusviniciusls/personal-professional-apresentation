package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory.SoftSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdSoftSkillsService {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private SoftSkillsFactory softSkillsFactory;

    public SoftSkillsResponse findById(UUID id){
        Optional<SoftSkills> optionalSoftSkills = softSkillsRepository.findById(id);
        if (optionalSoftSkills.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        SoftSkills softSkills = optionalSoftSkills.get();
        if (!centerEntityService.isStatusSuperEntity(softSkills)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        SoftSkillsResponse softSkillsResponse = softSkillsFactory.convertEntityInDto(softSkills);
        return softSkillsResponse;
    }
}
