package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory.HardSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdHardSkillsService {

    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private HardSkillsFactory hardSkillsFactory;

    public HardSkillsResponse findById(UUID id){
        Optional<HardSkills> optionalHardSkills = hardSkillsRepository.findById(id);
        if (optionalHardSkills.isEmpty()){
            throw new ResourceNotFoundException("Hard Skills Not Found Exception");
        }
        HardSkills hardSkills = optionalHardSkills.get();
        if (!centerEntityService.isStatusSuperEntity(hardSkills)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        HardSkillsResponse hardSkillsResponse = hardSkillsFactory.convertEntityInResponse(hardSkills);
        return hardSkillsResponse;
    }
}
