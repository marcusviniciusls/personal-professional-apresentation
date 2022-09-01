package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteGraduationService {

    @Autowired private GraduationRepository graduationRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Optional<Graduation> optionalGraduation = graduationRepository.findById(id);
        if (optionalGraduation.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        if (!centerEntityService.isStatusSuperEntity(optionalGraduation.get())){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        try {
            graduationRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex){
            Graduation graduation = optionalGraduation.get();
            graduation = (Graduation) centerEntityService.setDataToDelete(graduation);
            graduationRepository.save(graduation);
        }
    }
}
