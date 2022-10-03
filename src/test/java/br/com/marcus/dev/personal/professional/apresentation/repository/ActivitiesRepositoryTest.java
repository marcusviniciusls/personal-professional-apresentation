package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ActivitiesRepositoryTest {

    @Autowired private TestEntityManager testEntityManager;
    @Autowired private ActivitiesRepository activitiesRepository;

    @BeforeEach
    public void setupInit(){
        // Curso
        Course course = new Course(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Course courseStatusFalse = new Course(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));

        // Certificate
        Certificate certificate = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        Certificate certificateStatusFalse = new Certificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"));

        // HardSkills
        HardSkills hardSkills = new HardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"));
        HardSkills hardSkillsStatusFalse = new HardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));

        // Graduation
        Graduation graduation = new Graduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff707"));
        Graduation graduationStatusFalse = new Graduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff708"));

        // Professional Experience
        ProfessionalExperience professionalExperience = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff709"));
        ProfessionalExperience professionalExperienceStatusFalse = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff710"));

        // Professional Goal
        ProfessionalGoal professionalGoal = new ProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff711"));
        ProfessionalGoal professionalGoalStatusFalse = new ProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));

        // Soft Skills
        SoftSkills softSkills = new SoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff713"));
        SoftSkills softSkillsStatusFalse = new SoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff714"));

        //Atividades
        Activities activities = new Activities(LocalDate.now(), "teste", course);
        Activities activitiesStatusFalse = new Activities(LocalDate.now(), "teste", courseStatusFalse);
        activitiesStatusFalse.setStatus(false);

        // Vincular na atividade
        activities.setCertificate(certificate);
        activitiesStatusFalse.setCertificate(certificateStatusFalse);
        activities.setHardSkills(hardSkills);
        activitiesStatusFalse.setHardSkills(hardSkillsStatusFalse);
        activities.setGraduation(graduation);
        activitiesStatusFalse.setGraduation(graduationStatusFalse);
        activities.setProfessionalExperience(professionalExperience);
        activitiesStatusFalse.setProfessionalExperience(professionalExperienceStatusFalse);
        activities.setSoftSkills(softSkills);
        activitiesStatusFalse.setSoftSkills(softSkillsStatusFalse);
        activities.setProfessionalGoal(professionalGoal);
        activitiesStatusFalse.setProfessionalGoal(professionalGoalStatusFalse);

        // Salvar no Banco de Dados
        testEntityManager.persist(course);
        testEntityManager.persist(courseStatusFalse);
        testEntityManager.persist(certificate);
        testEntityManager.persist(certificateStatusFalse);
        testEntityManager.persist(hardSkills);
        testEntityManager.persist(hardSkillsStatusFalse);
        testEntityManager.persist(graduation);
        testEntityManager.persist(graduationStatusFalse);
        testEntityManager.persist(professionalExperience);
        testEntityManager.persist(professionalExperienceStatusFalse);
        testEntityManager.persist(professionalGoal);
        testEntityManager.persist(professionalGoalStatusFalse);
        testEntityManager.persist(softSkills);
        testEntityManager.persist(softSkillsStatusFalse);
        testEntityManager.persist(activities);
        testEntityManager.persist(activitiesStatusFalse);
    }

    @Test
    @DisplayName("Deve retornar verdadeiro buscando curso por Id")
    public void findByCourseTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByCourse(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar false pois nao achou nenhum Curso")
    public void findByNotFoundCourseTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByCourse(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        Optional<Activities> optionalActivitiesStatusFalse = activitiesRepository.findByCourse(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(false);
        Assertions.assertThat(optionalActivitiesStatusFalse.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("Deve retornar verdadeiro buscando certificacao por Id")
    public void findByCertificateTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar false pois nao achou nenhuma certificacao")
    public void findByNotFoundCertificateTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Activities> optionalActivitiesStatusFalse = activitiesRepository.findByCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(false);
        Assertions.assertThat(optionalActivitiesStatusFalse.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("Deve retornar verdadeiro buscando HardSkills por Id")
    public void findByHardSkillsTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByHardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar false pois nao achou nenhum HardSkills")
    public void findByNotFoundHardSkillsTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByHardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Activities> optionalActivitiesStatusFalse = activitiesRepository.findByHardSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(false);
        Assertions.assertThat(optionalActivitiesStatusFalse.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("Deve retornar verdadeiro buscando Graduation por Id")
    public void findByGraduationTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByGraduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff707"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar false pois nao achou nenhuma Graduation")
    public void findByNotFoundGraduationTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByGraduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Activities> optionalActivitiesStatusFalse = activitiesRepository.findByGraduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff708"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(false);
        Assertions.assertThat(optionalActivitiesStatusFalse.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("Deve retornar verdadeiro buscando Professional Experience por Id")
    public void findByProfessionalExperienceTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff709"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar false pois nao achou nenhum Professional Experience")
    public void findByNotFoundProfessionalExperienceTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Activities> optionalActivitiesStatusFalse = activitiesRepository.findByProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff710"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(false);
        Assertions.assertThat(optionalActivitiesStatusFalse.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("Deve retornar verdadeiro buscando Professional Goal por Id")
    public void findByProfessionalGoalTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff711"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar false pois nao achou nenhum Professional Goal")
    public void findByNotFoundProfessionalGoalTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findByProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Activities> optionalActivitiesStatusFalse = activitiesRepository.findByProfessionalGoal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(false);
        Assertions.assertThat(optionalActivitiesStatusFalse.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("Deve retornar verdadeiro buscando Soft Skills por Id")
    public void findBySoftSkillsTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findBySoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff713"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(true);
    }

    @Test
    @DisplayName("Deve retornar false pois nao achou nenhum Soft Skills")
    public void findByNotFoundSoftSkillsTest(){
        Optional<Activities> optionalActivities = activitiesRepository.findBySoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Optional<Activities> optionalActivitiesStatusFalse = activitiesRepository.findBySoftSkills(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff714"));
        Assertions.assertThat(optionalActivities.isPresent()).isEqualTo(false);
        Assertions.assertThat(optionalActivitiesStatusFalse.isPresent()).isEqualTo(false);
    }

    @Test
    @DisplayName("Verificar retorno do método findAll maior que 0")
    public void findAllTest(){
        List<Activities> listActivities = activitiesRepository.findAll();
        Assertions.assertThat(listActivities.size() > 0).isEqualTo(true);

    }
}
