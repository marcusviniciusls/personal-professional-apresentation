package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class DeleteCourseServiceTest {

    @Autowired private DeleteCourseService deleteCourseService;
    @Autowired private CourseRepository courseRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private ActivitiesRepository activitiesRepository;

    @Test
    @DisplayName("Apagar um course com sucesso")
    public void deleteTest(){
        Course course = new Course(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        courseRepository.save(course);
        Activities activities = new Activities(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        activities.setCourse(course);
        activitiesRepository.save(activities);
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701");
        BDDMockito.given(centerEntityService.setDataToDelete(Mockito.any(SuperEntity.class))).willReturn(course);
        deleteCourseService.delete(id);
        Optional<Course> optionalCourse = courseRepository.findById(id);
        Assertions.assertTrue(optionalCourse.isEmpty());
    }
}
