package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdCourseServiceTest {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired private FindByIdCourseService findByIdCourseService;

    @BeforeEach
    public void setupInit(){
        Course course = new Course("Name1", "description1", BigDecimal.ZERO
                , LocalDate.of(2022, 10,8), LocalDate.of(2022, 10,8)
                , LocalDate.of(2022, 10,8), LocalDate.of(2022, 10,8)
                , "logoImagem", StatusCourse.CONCLUSION, LevelCourse.BASIC);
        course.setStatus(true);
        course.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"));
        Course courseFalse = new Course("Name1", "description1", BigDecimal.ZERO, LocalDate.now(), LocalDate.now()
                , LocalDate.now(), LocalDate.now(), "logoImagem", StatusCourse.CONCLUSION, LevelCourse.BASIC);
        courseFalse.setStatus(false);
        courseFalse.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302"));
        courseRepository.save(course);
        courseRepository.save(courseFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar curso por ID com sucesso")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        CourseResponse courseResponse = findByIdCourseService.findById(id);
        Assertions.assertTrue(courseResponse != null);
        Assertions.assertEquals(id, courseResponse.getId());
        Assertions.assertEquals("description1", courseResponse.getDescription());
        Assertions.assertEquals("Name1", courseResponse.getName());
        Assertions.assertEquals(BigDecimal.ZERO, courseResponse.getDuration());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), courseResponse.getDateInitExpected());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), courseResponse.getDateFinishReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), courseResponse.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), courseResponse.getDateFinishExpected());
        Assertions.assertEquals(StatusCourse.CONCLUSION, courseResponse.getStatusCourse());
        Assertions.assertEquals(LevelCourse.BASIC, courseResponse.getLevelCourse());
    }

    @Test
    @DisplayName("Buscar curso por ID e retornar erro Status False")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCourseService.findById(id));
    }

    @Test
    @DisplayName("Buscar curso por ID e retornar erro Not Found")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCourseService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar curso por ID com sucesso")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Course course = findByIdCourseService.findByIdEntity(id);
        Assertions.assertTrue(course != null);
        Assertions.assertEquals(id, course.getId());
        Assertions.assertEquals("description1", course.getDescription());
        Assertions.assertEquals("Name1", course.getName());
        Assertions.assertEquals(BigDecimal.valueOf(0), course.getDuration());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateInitExpected());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateFinishReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateFinishExpected());
        Assertions.assertEquals(StatusCourse.CONCLUSION, course.getStatusCourse());
        Assertions.assertEquals(LevelCourse.BASIC, course.getLevelCourse());
    }

    @Test
    @DisplayName("Buscar curso por ID e retornar erro Status False")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCourseService.findByIdEntity(id));
    }

    @Test
    @DisplayName("Buscar curso por ID e retornar erro Not Found")
    public void findByIdEntityNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdCourseService.findByIdEntity(id));
    }
}
