package br.com.marcus.dev.personal.professional.apresentation.services.studyplan.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.factory.FrameworkFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory.ProfessionalExperienceFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.factory.TopicFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudyPlanFactory {

    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;
    @Autowired private FrameworkFactory frameworkFactory;
    @Autowired private TopicFactory topicFactory;
    @Autowired private CourseFactory courseFactory;
    @Autowired private ProfessionalExperienceFactory professionalExperienceFactory;
    @Autowired private AssignmentsFactory assignmentsFactory;
    @Autowired private GraduationFactory graduationFactory;
    @Autowired private CertificateFactory certificateFactory;

    public StudyPlanResponse convertEntityInResponse(StudyPlan studyPlan){
        // Campos Normais
        StudyPlanResponse studyPlanResponse = new StudyPlanResponse();
        studyPlanResponse.setName(studyPlan.getName());
        studyPlanResponse.setLevel(studyPlan.getLevel().getNumber());

        // Language Programmer
        LanguageProgrammerResponse languageProgrammerResponse = languageProgrammerFactory.convertEntityInResponse(studyPlan.getLanguageProgrammer());
        studyPlanResponse.setLanguageProgrammerResponse(languageProgrammerResponse);

        // Framework
        FrameworkResponse frameworkResponse = frameworkFactory.convertEntityInResponse(studyPlan.getFramework());
        studyPlanResponse.setFrameworkResponse(frameworkResponse);

        // Topic
        if (studyPlan.getListTopic().size() > 0){
            for (Topic topic : studyPlan.getListTopic()){
                TopicResponse topicResponse = topicFactory.convertEntityInResponse(topic);
                studyPlanResponse.addTopicResponse(topicResponse);
            }
        }

        // Course
        if (studyPlan.getListCourse().size() > 0){
            for (Course course : studyPlan.getListCourse()){
                CourseResponse courseResponse = courseFactory.convertEntityInDto(course);
                studyPlanResponse.addCourseResponse(courseResponse);
            }
        }

        // Professional Experience
        if (studyPlan.getListProfessionalExperience().size() > 0){
            for (ProfessionalExperience professionalExperience : studyPlan.getListProfessionalExperience()){
                ProfessionalExperienceResponse response = professionalExperienceFactory.convertEntityInResponse(professionalExperience);
                studyPlanResponse.addProfessionalExperienceResponse(response);
            }
        }

        // Assignments
        if (studyPlan.getListAssignments().size() > 0){
            for (Assignments assignments : studyPlan.getListAssignments()){
                AssignmentsResponse assignmentsResponse = assignmentsFactory.convertEntityInResponse(assignments);
                studyPlanResponse.addAssignmentsResponse(assignmentsResponse);
            }
        }

        // Graduation
        if (studyPlan.getListGraduation().size() > 0){
            for (Graduation graduation : studyPlan.getListGraduation()){
                GraduationResponse graduationResponse = graduationFactory.convertEntityInDto(graduation);
                studyPlanResponse.addGraduationResponse(graduationResponse);
            }
        }

        // Certificate
        if (studyPlan.getListCertificate().size() > 0){
            for (Certificate certificate : studyPlan.getListCertificate()){
                CertificateResponse certificateResponse = certificateFactory.convertEntityInResponse(certificate);
                studyPlanResponse.addCertificateResponse(certificateResponse);
            }
        }

        return studyPlanResponse;
    }
}
