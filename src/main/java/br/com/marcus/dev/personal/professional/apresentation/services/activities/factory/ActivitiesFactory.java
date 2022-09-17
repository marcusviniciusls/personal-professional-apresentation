package br.com.marcus.dev.personal.professional.apresentation.services.activities.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory.HardSkillsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory.ProfessionalExperienceFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.factory.ProfessionalGoalFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory.SoftSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ActivitiesFactory {

    @Autowired private CertificateFactory certificateFactory;
    @Autowired private CourseFactory courseFactory;
    @Autowired private GraduationFactory graduationFactory;
    @Autowired private HardSkillsFactory hardSkillsFactory;
    @Autowired private ProfessionalExperienceFactory professionalExperienceFactory;
    @Autowired private ProfessionalGoalFactory professionalGoalFactory;
    @Autowired private SoftSkillsFactory softSkillsFactory;

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

    public Activities convertGraduationToActivities(Graduation graduation){
        Activities activities = new Activities();
        activities.setGraduation(graduation);
        activities.setDate(LocalDate.now());
        activities.setDescription("Save Graduation");
        return activities;
    }

    public Activities convertProfessionalExperienceToActivities(ProfessionalExperience professionalExperience){
        Activities activities = new Activities();
        activities.setProfessionalExperience(professionalExperience);
        activities.setDate(LocalDate.now());
        activities.setDescription("Save Professional Experience");
        return activities;
    }

    public Activities convertProfessionalGoalToActivities(ProfessionalGoal professionalGoal){
        Activities activities = new Activities();
        activities.setProfessionalGoal(professionalGoal);
        activities.setDate(LocalDate.now());
        activities.setDescription("Save Professional Goal");
        return activities;
    }

    public ActivitiesResponse ConvertEntityInResponse(Activities activities){
        ActivitiesResponse activitiesResponse = new ActivitiesResponse();
        activitiesResponse.setDate(activities.getDate());
        activitiesResponse.setDescription(activities.getDescription());
        if (activities.getCertificate() != null){
            Certificate certificate = activities.getCertificate();
            CertificateResponse certificateResponse = certificateFactory.convertEntityInResponse(certificate);
            activitiesResponse.setCertificateResponse(certificateResponse);
        }
        if (activities.getCourse() != null){
            Course course = activities.getCourse();
            CourseResponse courseResponse = courseFactory.convertEntityInDto(course);
            activitiesResponse.setCourseResponse(courseResponse);
        }
        if (activities.getGraduation() != null){
            Graduation graduation = activities.getGraduation();
            GraduationResponse graduationResponse = graduationFactory.convertEntityInDto(graduation);
            activitiesResponse.setGraduationResponse(graduationResponse);
        }
        if (activities.getHardSkills() != null){
            HardSkills hardSkills = activities.getHardSkills();
            HardSkillsResponse hardSkillsResponse = hardSkillsFactory.convertEntityInResponse(hardSkills);
            activitiesResponse.setHardSkillsResponse(hardSkillsResponse);
        }
        if (activities.getProfessionalExperience() != null){
            ProfessionalExperience professionalExperience = activities.getProfessionalExperience();
            ProfessionalExperienceResponse response = professionalExperienceFactory.convertEntityInResponse(professionalExperience);
            activitiesResponse.setProfessionalExperienceResponse(response);
        }
        if (activities.getProfessionalGoal() != null){
            ProfessionalGoal professionalGoal = activities.getProfessionalGoal();
            ProfessionalGoalResponse response = professionalGoalFactory.convertEntityInResponse(professionalGoal);
            activitiesResponse.setProfessionalGoalResponse(response);
        }
        if (activities.getSoftSkills() != null){
            SoftSkills softSkills = activities.getSoftSkills();
            SoftSkillsResponse softSkillsResponse = softSkillsFactory.convertEntityInDto(softSkills);
            activitiesResponse.setSoftSkillsResponse(softSkillsResponse);
        }

        return activitiesResponse;
    }
}
