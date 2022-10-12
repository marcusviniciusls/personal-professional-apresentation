package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.DeleteActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteHardSkillsService {

    @Autowired private FindByIdHardSkillsService findByIdHardSkillsService;
    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DeleteActivitiesService deleteActivitiesService;

    public void delete(UUID id){
        HardSkills hardSkills = findByIdHardSkillsService.findByIdEntity(id);
        deleteActivitiesService.deleteMovementHardSkills(hardSkills.getId());
        try{
            hardSkillsRepository.delete(hardSkills);
        } catch(DataIntegrityViolationException ex){
            hardSkills = (HardSkills) centerEntityService.setDataToDelete(hardSkills);
            hardSkillsRepository.save(hardSkills);
        }
    }
}
