package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnableSoftSkillsService {

    @Autowired private FindByIdSoftSkillsService findByIdSoftSkillsService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private SoftSkillsRepository softSkillsRepository;

    public void enable(UUID id){
        SoftSkills softSkills = findByIdSoftSkillsService.findByIdSoftSkills(id);
        softSkills = (SoftSkills) centerEntityService.setDataToUpdate(softSkills);
        if (!softSkills.isStatusHas()){
            softSkills.setStatusHas(true);
            softSkills = softSkillsRepository.save(softSkills);
        }
    }
}
