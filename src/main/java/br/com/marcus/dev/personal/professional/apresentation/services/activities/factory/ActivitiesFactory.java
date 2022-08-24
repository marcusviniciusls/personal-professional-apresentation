package br.com.marcus.dev.personal.professional.apresentation.services.activities.factory;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ActivitiesFactory {

    public Activities convertSoftSkillsToActivities(SoftSkills softSkills){
        Activities activities = new Activities();
        activities.setSoftSkills(softSkills);
        activities.setDate(LocalDate.now());
        activities.setDescription("Save Soft Skills");
        return activities;
    }
}
