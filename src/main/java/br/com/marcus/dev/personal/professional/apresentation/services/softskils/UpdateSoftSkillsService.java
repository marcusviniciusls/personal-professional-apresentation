package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory.SoftSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateSoftSkillsService {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private FindByIdSoftSkillsService findByIdSoftSkillsService;
    @Autowired private SoftSkillsFactory softSkillsFactory;
    @Autowired private CenterEntityService centerEntityService;

    public SoftSkillsResponse update(SoftSkillsFormUpdate softSkillsFormUpdate, UUID id){
        SoftSkills softSkills = findByIdSoftSkillsService.findByIdSoftSkills(id);
        softSkills = softSkillsFactory.convertFormUpdateToEntity(softSkillsFormUpdate, softSkills);
        softSkills = (SoftSkills) centerEntityService.setDataToUpdate(softSkills);
        softSkills = softSkillsRepository.save(softSkills);
        SoftSkillsResponse softSkillsResponse = softSkillsFactory.convertEntityInDto(softSkills);
        return softSkillsResponse;
    }
}
