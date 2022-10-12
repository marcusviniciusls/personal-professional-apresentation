package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.DeleteActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteGraduationService {

    @Autowired private GraduationRepository graduationRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DeleteActivitiesService deleteActivitiesService;
    @Autowired private FindByIdGraduationService findByIdGraduationService;

    public void delete(UUID id){
        Graduation graduation = findByIdGraduationService.findByIdEntity(id);
        deleteActivitiesService.deleteMovementGraduation(graduation.getId());
        try {
            graduationRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex){
            graduation = (Graduation) centerEntityService.setDataToDelete(graduation);
            graduationRepository.save(graduation);
        }
    }
}
