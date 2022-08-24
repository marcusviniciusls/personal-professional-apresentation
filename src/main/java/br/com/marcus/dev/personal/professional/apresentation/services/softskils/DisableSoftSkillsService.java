package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DisableSoftSkillsService {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private FindByIdSoftSkillsService findByIdSoftSkillsService;
    @Autowired private CenterEntityService centerEntityService;

    public void disable(UUID id){
        SoftSkills softSkills = findByIdSoftSkillsService.findByIdSoftSkills(id);
        softSkills = (SoftSkills) centerEntityService.setDataToUpdate(softSkills);
        if (softSkills.isStatusHas()){
            softSkills.setStatusHas(false);
            softSkills = softSkillsRepository.save(softSkills);
        }
    }
}
