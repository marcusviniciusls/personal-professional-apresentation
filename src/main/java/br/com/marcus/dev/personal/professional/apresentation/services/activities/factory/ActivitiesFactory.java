package br.com.marcus.dev.personal.professional.apresentation.services.activities.factory;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivitiesFactory {

    public Activities convertSuperEntityToActivities(SuperEntity superEntity){
        Activities activities = new Activities();
        if (superEntity.getClass().isInstance(SoftSkills.class)){
            activities
        }
    }
}
