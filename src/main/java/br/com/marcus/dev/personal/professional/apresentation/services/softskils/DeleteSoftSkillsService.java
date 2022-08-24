package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteSoftSkillsService {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID uuid){
        try{
            Optional<SoftSkills> optionalSoftSkills = softSkillsRepository.findById(uuid);
            if (optionalSoftSkills.isEmpty()){
                throw new ResourceNotFoundException("ID Not Found Exception");
            }
            if (!centerEntityService.isStatusSuperEntity(optionalSoftSkills.get())){
                throw new ResourceNotFoundException("ID Not Found Exception");
            }
            softSkillsRepository.deleteById(uuid);
        } catch(DataIntegrityViolationException ex){
            Optional<SoftSkills> optionalSoftSkills = softSkillsRepository.findById(uuid);
            SoftSkills softSkills = optionalSoftSkills.get();
            softSkills = (SoftSkills) centerEntityService.setDataToDelete(softSkills);
            softSkillsRepository.save(softSkills);
        }
    }
}
