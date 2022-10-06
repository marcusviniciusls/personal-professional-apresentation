package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteActivitiesServiceTest {

    @Autowired private CertificateRepository certificateRepository;
    @Autowired private ActivitiesRepository activitiesRepository;
    @Autowired private DeleteActivitiesService deleteActivitiesService;
    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private SoftSkillsRepository softSkillsRepository;

    @BeforeEach
    public void setupInit(){
        // Certificate
        Certificate certificate = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        certificateRepository.save(certificate);

        // HardSkills
        HardSkills hardSkills = new HardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        hardSkillsRepository.save(hardSkills);

        // Course
        Course course = new Course(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        courseRepository.save(course);

        // Graduation
        Graduation graduation = new Graduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"));
        graduationRepository.save(graduation);

        // Professional Experience
        ProfessionalExperience professionalExperience = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"));
        professionalExperienceRepository.save(professionalExperience);

        // Professional Goal
        ProfessionalGoal professionalGoal = new ProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        professionalGoalRepository.save(professionalGoal);

        // SoftSkills
        SoftSkills softSkills = new SoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff708"));
        softSkillsRepository.save(softSkills);

        //Activities
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        activities.setCertificate(certificate);
        activities.setHardSkills(hardSkills);
        activities.setCourse(course);
        activities.setGraduation(graduation);
        activities.setProfessionalExperience(professionalExperience);
        activities.setProfessionalGoal(professionalGoal);
        activities.setSoftSkills(softSkills);

        // Salvar Activities
        activitiesRepository.save(activities);
    }

    @Test
    @DisplayName("Apagar uma Activities para a Certificacao com sucesso")
    public void deleteMovementCertificateTest(){
        deleteActivitiesService.deleteMovementCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Activities> optionalActivities = activitiesRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        Assertions.assertTrue(optionalActivities.isEmpty());
    }

    @Test
    @DisplayName("Apagar uma Activities e nao encontrar a Certificacao")
    public void deleteMovementCertificateResourceNotFoundExceptionTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            deleteActivitiesService.deleteMovementCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        });
    }

    @Test
    @DisplayName("Apagar uma Activities para o HardSkills com sucesso")
    public void deleteMovementHardSkillsTest(){
        deleteActivitiesService.deleteMovementHardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        Optional<Activities> optionalActivities = activitiesRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        Assertions.assertTrue(optionalActivities.isEmpty());
    }

    @Test
    @DisplayName("Apagar uma Activities e nao encontrar a HardSkills")
    public void deleteMovementHardSkillsResourceNotFoundExceptionTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            deleteActivitiesService.deleteMovementHardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff100"));
        });
    }

    @Test
    @DisplayName("Apagar uma Activities para o Course com sucesso")
    public void deleteMovementCourseTest(){
        deleteActivitiesService.deleteMovementCourse(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        Optional<Activities> optionalActivities = activitiesRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        Assertions.assertTrue(optionalActivities.isEmpty());
    }

    @Test
    @DisplayName("Apagar uma Activities e nao encontrar o Course")
    public void deleteMovementCourseResourceNotFoundExceptionTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            deleteActivitiesService.deleteMovementCourse(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff100"));
        });
    }

    @Test
    @DisplayName("Apagar uma Activities para o Course com sucesso")
    public void deleteMovementGraduationTest(){
        deleteActivitiesService.deleteMovementGraduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"));
        Optional<Activities> optionalActivities = activitiesRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"));
        Assertions.assertTrue(optionalActivities.isEmpty());
    }

    @Test
    @DisplayName("Apagar uma Activities e nao encontrar o Graduation")
    public void deleteMovementGraduationResourceNotFoundExceptionTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            deleteActivitiesService.deleteMovementGraduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff100"));
        });
    }

    @Test
    @DisplayName("Apagar uma Activities para o Professional Experience com sucesso")
    public void deleteMovementProfessionalExperienceTest(){
        deleteActivitiesService.deleteMovementProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"));
        Optional<Activities> optionalActivities = activitiesRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"));
        Assertions.assertTrue(optionalActivities.isEmpty());
    }

    @Test
    @DisplayName("Apagar uma Activities e nao encontrar o Professional Experience")
    public void deleteMovementProfessionalExperienceResourceNotFoundExceptionTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            deleteActivitiesService.deleteMovementProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff100"));
        });
    }

    @Test
    @DisplayName("Apagar uma Activities para o Professional Goal com sucesso")
    public void deleteMovementProfessionalGoalTest(){
        deleteActivitiesService.deleteMovementProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        Optional<Activities> optionalActivities = activitiesRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        Assertions.assertTrue(optionalActivities.isEmpty());
    }

    @Test
    @DisplayName("Apagar uma Activities e nao encontrar o Professional Goal")
    public void deleteMovementProfessionalGoalResourceNotFoundExceptionTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            deleteActivitiesService.deleteMovementProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff100"));
        });
    }

    @Test
    @DisplayName("Apagar uma Activities para o SoftSkills com sucesso")
    public void deleteMovementSoftSkillsTest(){
        deleteActivitiesService.deleteMovementSoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff708"));
        Optional<Activities> optionalActivities = activitiesRepository.findById(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff708"));
        Assertions.assertTrue(optionalActivities.isEmpty());
    }

    @Test
    @DisplayName("Apagar uma Activities e nao encontrar o SoftSkills")
    public void deleteMovementSoftSkillsResourceNotFoundExceptionTest(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            deleteActivitiesService.deleteMovementSoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff100"));
        });
    }
}
