package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.interfaces.SaveMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveActivitiesService implements SaveMovement {

    @Autowired private ActivitiesRepository activitiesRepository;

    @Override
    public void saveMovement(SuperEntity superEntity) {
        activitiesRepository.save(superEntity);
    }
}
