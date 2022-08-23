package br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import org.springframework.stereotype.Component;

@Component
public class SoftSkillsFactory {

    public SoftSkillsResponse convertEntityInDto(SoftSkills softSkills){
        SoftSkillsResponse softSkillsResponse = new SoftSkillsResponse();
        softSkillsResponse.setName(softSkills.getName());
        softSkillsResponse.setStatusHas(softSkills.isStatusHas());
        return softSkillsResponse;
    }

    public SoftSkills convertFormInEntityToSave(SoftSkillsFormSave softSkillsFormSave){
        SoftSkills softSkills = new SoftSkills();
        softSkills.setName(softSkillsFormSave.getName());
        softSkills.setStatusHas(true);
        return softSkills;
    }
}
