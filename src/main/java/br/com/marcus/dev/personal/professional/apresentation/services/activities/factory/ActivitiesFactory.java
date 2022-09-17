package br.com.marcus.dev.personal.professional.apresentation.services.activities.factory;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
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

    public Activities convertCourseToActivities(Course course){
        Activities activities = new Activities();
        activities.setCourse(course);
        activities.setDate(LocalDate.now());
        activities.setDescription("Save Course");
        return activities;
    }

    public Activities convertCertificateToActivities(Certificate certificate){
        Activities activities = new Activities();
        activities.setCertificate(certificate);
        activities.setDate(LocalDate.now());
        activities.setDescription("Save Certificate");
        return activities;
    }

    public Activities convertHardSkillsToActivities(HardSkills hardSkills){
        Activities activities = new Activities();
        activities.setHardSkills(hardSkills);
        activities.setDate(LocalDate.now());
        activities.setDescription("Save Hard Skills");
        return activities;
    }
}
