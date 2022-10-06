package br.com.marcus.dev.personal.professional.apresentation.services.activities;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveActivitiesServiceTest {

    @Autowired private SaveActivitiesService saveActivitiesService;
    @MockBean private ActivitiesRepository activitiesRepository;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @DisplayName("Salvar uma Activities com SoftSkills")
    public void saveMovementSoftSkillsTest(){
        SoftSkills softSkills = new SoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(activities);
        BDDMockito.given(activitiesRepository.findBySoftSkills(Mockito.any(UUID.class))).willReturn(Optional.of(activities));
        saveActivitiesService.saveMovementSoftSkills(softSkills);
        Optional<Activities> optionalActivities = activitiesRepository.findBySoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertTrue(optionalActivities.isPresent());
    }

    @Test
    @DisplayName("Salvar uma Activities com Course")
    public void saveMovementCourseTest(){
        Course course = new Course(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(activities);
        BDDMockito.given(activitiesRepository.findByCourse(Mockito.any(UUID.class))).willReturn(Optional.of(activities));
        saveActivitiesService.saveMovementCourse(course);
        Optional<Activities> optionalActivities = activitiesRepository.findByCourse(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertTrue(optionalActivities.isPresent());
    }

    @Test
    @DisplayName("Salvar uma Activities com Certificate")
    public void saveMovementCertificateTest(){
        Certificate certificate = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(activities);
        BDDMockito.given(activitiesRepository.findByCertificate(Mockito.any(UUID.class))).willReturn(Optional.of(activities));
        saveActivitiesService.saveMovementCertificate(certificate);
        Optional<Activities> optionalActivities = activitiesRepository.findByCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertTrue(optionalActivities.isPresent());
    }

    @Test
    @DisplayName("Salvar uma Activities com HardSkills")
    public void saveMovementHardSkillsTest(){
        HardSkills hardSkills = new HardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(activities);
        BDDMockito.given(activitiesRepository.findByHardSkills(Mockito.any(UUID.class))).willReturn(Optional.of(activities));
        saveActivitiesService.saveMovementHardSkills(hardSkills);
        Optional<Activities> optionalActivities = activitiesRepository.findByHardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertTrue(optionalActivities.isPresent());
    }

    @Test
    @DisplayName("Salvar uma Activities com Graduation")
    public void saveMovementGraduationTest(){
        Graduation graduation = new Graduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(activities);
        BDDMockito.given(activitiesRepository.findByGraduation(Mockito.any(UUID.class))).willReturn(Optional.of(activities));
        saveActivitiesService.saveMovementGraduation(graduation);
        Optional<Activities> optionalActivities = activitiesRepository.findByGraduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertTrue(optionalActivities.isPresent());
    }

    @Test
    @DisplayName("Salvar uma Activities com Professional Experience")
    public void saveMovementProfessionalExperienceTest(){
        ProfessionalExperience professionalExperience = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(activities);
        BDDMockito.given(activitiesRepository.findByProfessionalExperience(Mockito.any(UUID.class))).willReturn(Optional.of(activities));
        saveActivitiesService.saveMovementProfessionalExperience(professionalExperience);
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertTrue(optionalActivities.isPresent());
    }

    @Test
    @DisplayName("Salvar uma Activities com Professional Goal")
    public void saveMovementProfessionalGoalTest(){
        ProfessionalGoal professionalGoal = new ProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(activities);
        BDDMockito.given(activitiesRepository.findByProfessionalGoal(Mockito.any(UUID.class))).willReturn(Optional.of(activities));
        saveActivitiesService.saveMovementProfessionalGoal(professionalGoal);
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertTrue(optionalActivities.isPresent());
    }
}
