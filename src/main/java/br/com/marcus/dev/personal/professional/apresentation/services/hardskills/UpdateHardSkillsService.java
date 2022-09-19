package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory.HardSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateHardSkillsService {

    @Autowired private FindByIdHardSkillsService findByIdHardSkillsService;
    @Autowired private HardSkillsFactory hardSkillsFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private HardSkillsRepository hardSkillsRepository;

    public void update(UUID id, HardSkillsUpdateForm hardSkillsUpdateForm){
        HardSkills hardSkills = findByIdHardSkillsService.findByIdEntity(id);
        hardSkills = hardSkillsFactory.convertUpdateFormInEntity(hardSkillsUpdateForm, hardSkills);
        hardSkills = (HardSkills) centerEntityService.setDataToUpdate(hardSkills);
        hardSkillsRepository.save(hardSkills);
    }
}
