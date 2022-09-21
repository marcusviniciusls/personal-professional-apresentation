package br.com.marcus.dev.personal.professional.apresentation.services.studyplan.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.*;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.*;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.FindByIdAssignmentsService;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindByIdCertificateService;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.course.FindByIdCourseService;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindByIdFrameworkService;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.factory.FrameworkFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.FindByIdGraduationService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindByIdLanguageProgrammerService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.FindByIdProfessionalExperienceService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory.ProfessionalExperienceFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.FindByIdEntityStudyPlanService;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.FindByIdStudyPlanService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.FindByIdTopicService;
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
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private TopicRepository topicRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private CenterEntityService centerEntityService;

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

    public StudyPlan convertSaveFormInEntity(StudyPlanSaveForm studyPlanSaveForm){
        StudyPlan studyPlan = new StudyPlan();

        // Dados Normais
        studyPlan.setName(studyPlanSaveForm.getName());
        studyPlan.setLevel(Level.toEnum(studyPlanSaveForm.getLevel()));

        // Framework
        if (studyPlanSaveForm.getFrameworkId() != null){
            Framework framework = frameworkRepository.findById(studyPlanSaveForm.getFrameworkId()).get();
            studyPlan.setFramework(framework);
        }

        // Language Programmer
        if (studyPlanSaveForm.getLanguageProgrammerId() != null){
            LanguageProgrammer languageProgrammer = languageProgrammerRepository.findById(studyPlanSaveForm.getLanguageProgrammerId()).get();
            studyPlan.setLanguageProgrammer(languageProgrammer);
        }

        // Topic
        if (studyPlanSaveForm.getListTopic().size() > 0){
            for (ListTopic listTopic : studyPlanSaveForm.getListTopic()){
                Topic topic = topicRepository.findById(listTopic.getId()).get();
                studyPlan.addTopic(topic);
                topic = (Topic) centerEntityService.setDataToSave(topic);
                topicRepository.save(topic);
            }
        }

        // Course
        if (studyPlanSaveForm.getListCourse().size() > 0){
            for (ListCourse listCourse : studyPlanSaveForm.getListCourse()){
                Course course = courseRepository.findById(listCourse.getId()).get();
                studyPlan.addCourse(course);
            }
        }

        // Professional Experience
        if (studyPlanSaveForm.getListProfessionalExperience().size() > 0){
            for (ListProfessionalExperience listProfessionalExperience : studyPlanSaveForm.getListProfessionalExperience()){
                ProfessionalExperience entity = professionalExperienceRepository.findById(listProfessionalExperience.getId()).get();
                studyPlan.addProfessionalExperience(entity);
            }
        }

        // Assignments
        if (studyPlanSaveForm.getListAssignments().size() > 0){
            for (ListAssignments listAssignments : studyPlanSaveForm.getListAssignments()){
                Assignments assignments = assignmentsRepository.findById(listAssignments.getId()).get();
                studyPlan.addAssignments(assignments);
            }
        }

        // Graduation
        if (studyPlanSaveForm.getListGraduation().size() > 0){
            for (ListGraduation listGraduation : studyPlanSaveForm.getListGraduation()){
                Graduation graduation = graduationRepository.findById(listGraduation.getId()).get();
                studyPlan.addGraduation(graduation);
            }
        }

        // Certificate
        if (studyPlanSaveForm.getListCertificate().size() > 0){
            for (ListCertificate listCertificate : studyPlanSaveForm.getListCertificate()){
                Certificate certificate = certificateRepository.findById(listCertificate.getId()).get();
                studyPlan.addCertificate(certificate);
            }
        }

        return studyPlan;
    }
}
