package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
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

        //Atividades
        Activities activitiesCourse = new Activities(LocalDate.now(), "teste", course);
        Activities activitiesCourseStatusFalse = new Activities(LocalDate.now(), "teste", courseStatusFalse);
        activitiesCourseStatusFalse.setStatus(false);

        // Vincular na atividade
        activitiesCourse.setCertificate(certificate);
        activitiesCourseStatusFalse.setCertificate(certificateStatusFalse);

        // Salvar no Banco de Dados
        testEntityManager.persist(course);
        testEntityManager.persist(courseStatusFalse);
        testEntityManager.persist(certificate);
        testEntityManager.persist(certificateStatusFalse);
        testEntityManager.persist(activitiesCourse);
        testEntityManager.persist(activitiesCourseStatusFalse);
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
}
