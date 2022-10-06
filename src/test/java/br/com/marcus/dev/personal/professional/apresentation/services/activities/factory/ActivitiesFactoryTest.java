package br.com.marcus.dev.personal.professional.apresentation.services.activities.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ActivitiesResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ActivitiesFactoryTest {

    @Autowired private ActivitiesFactory activitiesFactory;

    @Test
    @DisplayName("Converter SoftSkills em Activities")
    public void convertSoftSkillsToActivitiesTest(){
        SoftSkills softSkills = new SoftSkills();
        Activities activities = activitiesFactory.convertSoftSkillsToActivities(softSkills);
        Assertions.assertTrue(activities.getSoftSkills() != null);
        Assertions.assertTrue(activities.getDate() != null);
        Assertions.assertEquals("Save Soft Skills", activities.getDescription());
    }

    @Test
    @DisplayName("Converter Course em Activities")
    public void convertCourseToActivitiesTest(){
        Course course = new Course();
        Activities activities = activitiesFactory.convertCourseToActivities(course);
        Assertions.assertTrue(activities.getCourse() != null);
        Assertions.assertTrue(activities.getDate() != null);
        Assertions.assertEquals("Save Course", activities.getDescription());
    }

    @Test
    @DisplayName("Converter Certificate em Activities")
    public void convertCertificateToActivitiesTest(){
        Certificate certificate = new Certificate();
        Activities activities = activitiesFactory.convertCertificateToActivities(certificate);
        Assertions.assertTrue(activities.getCertificate() != null);
        Assertions.assertTrue(activities.getDate() != null);
        Assertions.assertEquals("Save Certificate", activities.getDescription());
    }

    @Test
    @DisplayName("Converter HardSkills em Activities")
    public void convertHardSkillsToActivitiesTest(){
        HardSkills hardSkills = new HardSkills();
        Activities activities = activitiesFactory.convertHardSkillsToActivities(hardSkills);
        Assertions.assertTrue(activities.getHardSkills() != null);
        Assertions.assertTrue(activities.getDate() != null);
        Assertions.assertEquals("Save Hard Skills", activities.getDescription());
    }

    @Test
    @DisplayName("Converter Graduation em Activities")
    public void convertGraduationToActivitiesTest(){
        Graduation graduation = new Graduation();
        Activities activities = activitiesFactory.convertGraduationToActivities(graduation);
        Assertions.assertTrue(activities.getGraduation() != null);
        Assertions.assertTrue(activities.getDate() != null);
        Assertions.assertEquals("Save Graduation", activities.getDescription());
    }

    @Test
    @DisplayName("Converter Professional Experience em Activities")
    public void convertProfessionalExperienceToActivitiesTest(){
        ProfessionalExperience professionalExperience = new ProfessionalExperience();
        Activities activities = activitiesFactory.convertProfessionalExperienceToActivities(professionalExperience);
        Assertions.assertTrue(activities.getProfessionalExperience() != null);
        Assertions.assertTrue(activities.getDate() != null);
        Assertions.assertEquals("Save Professional Experience", activities.getDescription());
    }

    @Test
    @DisplayName("Converter Professional Goal em Activities")
    public void convertProfessionalGoalToActivitiesTest(){
        ProfessionalGoal professionalGoal = new ProfessionalGoal();
        Activities activities = activitiesFactory.convertProfessionalGoalToActivities(professionalGoal);
        Assertions.assertTrue(activities.getProfessionalGoal() != null);
        Assertions.assertTrue(activities.getDate() != null);
        Assertions.assertEquals("Save Professional Goal", activities.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Response (Certificate)")
    public void convertEntityInResponseCertificateTest(){
        Partner partner = new Partner("name", "urlImage", new BranchActivity("teste"), "description");
        Certificate certificate = new Certificate("Teste", "logoImage", partner);
        Activities activities = new Activities();
        activities.setCertificate(certificate);
        activities.setDate(LocalDate.of(2022, 6, 10));
        activities.setDescription("Test");
        ActivitiesResponse activitiesResponse = activitiesFactory.convertEntityInResponse(activities);
        Assertions.assertEquals(LocalDate.of(2022, 6, 10), activitiesResponse.getDate());
        Assertions.assertTrue(activitiesResponse.getCertificateResponse() != null);
        Assertions.assertEquals("Test", activitiesResponse.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Response (Course)")
    public void convertEntityInResponseCourseTest(){
        Course course = new Course("name", "description", new BigDecimal("0"), LocalDate.now(), LocalDate.now()
        , LocalDate.now(), LocalDate.now(), "logoImage", StatusCourse.NOTSTARTED, LevelCourse.ADVANCED);
        Activities activities = new Activities();
        activities.setCourse(course);
        activities.setDate(LocalDate.of(2022, 6, 10));
        activities.setDescription("Test");
        ActivitiesResponse activitiesResponse = activitiesFactory.convertEntityInResponse(activities);
        Assertions.assertEquals(LocalDate.of(2022, 6, 10), activitiesResponse.getDate());
        Assertions.assertTrue(activitiesResponse.getCourseResponse() != null);
        Assertions.assertEquals("Test", activitiesResponse.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Response (Graduation)")
    public void convertEntityInResponseGraduationTest(){
        Partner partner = new Partner("name", "urlImage", new BranchActivity("teste"), "description");
        Graduation graduation = new Graduation("name", BigDecimal.ZERO, LocalDate.now(), LocalDate.now(), "location"
        , BigDecimal.ZERO, SituationGraduation.CONCLUSION, TypeGraduation.GRADUATION, partner);
        Activities activities = new Activities();
        activities.setGraduation(graduation);
        activities.setDate(LocalDate.of(2022, 6, 10));
        activities.setDescription("Test");
        ActivitiesResponse activitiesResponse = activitiesFactory.convertEntityInResponse(activities);
        Assertions.assertEquals(LocalDate.of(2022, 6, 10), activitiesResponse.getDate());
        Assertions.assertTrue(activitiesResponse.getGraduationResponse() != null);
        Assertions.assertEquals("Test", activitiesResponse.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Response (HardSkills)")
    public void convertEntityInResponseHardSkillsTest(){
        HardSkills hardSkills = new HardSkills("name", "description", Level.ADVANCED);
        Activities activities = new Activities();
        activities.setHardSkills(hardSkills);
        activities.setDate(LocalDate.of(2022, 6, 10));
        activities.setDescription("Test");
        ActivitiesResponse activitiesResponse = activitiesFactory.convertEntityInResponse(activities);
        Assertions.assertEquals(LocalDate.of(2022, 6, 10), activitiesResponse.getDate());
        Assertions.assertTrue(activitiesResponse.getHardSkillsResponse() != null);
        Assertions.assertEquals("Test", activitiesResponse.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Response (Professional Experience)")
    public void convertEntityInResponseProfessionalExperienceTest(){
        Partner partner = new Partner("name", "urlImage", new BranchActivity("teste"), "description");
        Office office = new Office("name", "description", OfficeLevel.INTERNSHIP);
        ProfessionalExperience professionalExperience = new ProfessionalExperience(LocalDate.now(), LocalDate.now()
        , OfficeEnum.CLT, StatusWork.OLD, partner, office, "description");
        Activities activities = new Activities();
        activities.setProfessionalExperience(professionalExperience);
        activities.setDate(LocalDate.of(2022, 6, 10));
        activities.setDescription("Test");
        ActivitiesResponse activitiesResponse = activitiesFactory.convertEntityInResponse(activities);
        Assertions.assertEquals(LocalDate.of(2022, 6, 10), activitiesResponse.getDate());
        Assertions.assertTrue(activitiesResponse.getProfessionalExperienceResponse() != null);
        Assertions.assertEquals("Test", activitiesResponse.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Response (Professional Goal)")
    public void convertEntityInResponseProfessionalGoalTest(){
        ProfessionalGoal professionalGoal = new ProfessionalGoal("description", "office");
        Activities activities = new Activities();
        activities.setProfessionalGoal(professionalGoal);
        activities.setDate(LocalDate.of(2022, 6, 10));
        activities.setDescription("Test");
        ActivitiesResponse activitiesResponse = activitiesFactory.convertEntityInResponse(activities);
        Assertions.assertEquals(LocalDate.of(2022, 6, 10), activitiesResponse.getDate());
        Assertions.assertTrue(activitiesResponse.getProfessionalGoalResponse() != null);
        Assertions.assertEquals("Test", activitiesResponse.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Response (Soft Skills)")
    public void convertEntityInResponseSoftSkillsTest(){
        SoftSkills softSkills = new SoftSkills("name", true);
        Activities activities = new Activities();
        activities.setSoftSkills(softSkills);
        activities.setDate(LocalDate.of(2022, 6, 10));
        activities.setDescription("Test");
        ActivitiesResponse activitiesResponse = activitiesFactory.convertEntityInResponse(activities);
        Assertions.assertEquals(LocalDate.of(2022, 6, 10), activitiesResponse.getDate());
        Assertions.assertTrue(activitiesResponse.getSoftSkillsResponse() != null);
        Assertions.assertEquals("Test", activitiesResponse.getDescription());
    }
}
