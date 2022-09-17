package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory.HardSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveHardSkillsService {

    @Autowired private HardSkillsFactory hardSkillsFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private SaveActivitiesService saveActivitiesService;

    public HardSkillsResponse save(HardSkillsSaveForm hardSkillsSaveForm){
        HardSkills hardSkills = hardSkillsFactory.convertRequestSaveInEntity(hardSkillsSaveForm);
        hardSkills = (HardSkills) centerEntityService.setDataToSave(hardSkills);
        hardSkills = hardSkillsRepository.save(hardSkills);
        saveActivitiesService.saveMovementHardSkills(hardSkills);
        return hardSkillsFactory.convertEntityInResponse(hardSkills);
    }
}