package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteActivitiesService {

    @Autowired private ActivitiesRepository activitiesRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void deleteMovementCertificate(UUID idCertificate){
        Optional<Activities> optionalActivities = activitiesRepository.findByCertificate(idCertificate);
        if (optionalActivities.isEmpty()){
            throw new ResourceNotFoundException("Activities Certificate Not Found Exception");
        }
        Activities activities = optionalActivities.get();
        try {
            activitiesRepository.delete(activities);
        } catch(DataIntegrityViolationException ex){
            activities = (Activities) centerEntityService.setDataToDelete(activities);
            activitiesRepository.save(activities);
        }
    }
}
