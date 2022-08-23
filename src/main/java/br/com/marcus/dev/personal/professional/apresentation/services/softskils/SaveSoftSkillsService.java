package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory.SoftSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveSoftSkillsService {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private SoftSkillsFactory softSkillsFactory;
    @Autowired private CenterEntityService centerEntityService;

    public SoftSkillsResponse save(SoftSkillsFormSave softSkillsFormSave){
        SoftSkills softSkills = softSkillsFactory.convertFormInEntityToSave(softSkillsFormSave);
        softSkills = (SoftSkills) centerEntityService.setDataToSave(softSkills);
        softSkills = softSkillsRepository.save(softSkills);
        SoftSkillsResponse softSkillsResponse = softSkillsFactory.convertEntityInDto(softSkills);
        return softSkillsResponse;
    }
}
