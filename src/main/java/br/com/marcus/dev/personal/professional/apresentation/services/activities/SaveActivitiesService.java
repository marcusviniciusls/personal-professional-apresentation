package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.factory.ActivitiesFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveActivitiesService {

    @Autowired private ActivitiesRepository activitiesRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ActivitiesFactory activitiesFactory;

    public void saveMovementSoftSkills(SoftSkills softSkills) {
        Activities activities = activitiesFactory.convertSoftSkillsToActivities(softSkills);
        activities = (Activities) centerEntityService.setDataToSave(activities);
        activitiesRepository.save(activities);
    }
}
