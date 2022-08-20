package br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory;

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
}
