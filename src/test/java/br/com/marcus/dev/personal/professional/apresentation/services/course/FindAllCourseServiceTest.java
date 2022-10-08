package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllCourseServiceTest {

    @Autowired private CourseRepository courseRepository;
    @Autowired private FindAllCourseService findAllCourseService;

    @BeforeEach
    public void setupInit(){
        Course course1 = new Course("Name1", "description1", BigDecimal.ZERO, LocalDate.now(), LocalDate.now()
        , LocalDate.now(), LocalDate.now(), "logoImagem", StatusCourse.CONCLUSION, LevelCourse.BASIC);
        Course course2 = new Course("Name1", "description1", BigDecimal.ZERO, LocalDate.now(), LocalDate.now()
                , LocalDate.now(), LocalDate.now(), "logoImagem", StatusCourse.CONCLUSION, LevelCourse.BASIC);
        Course course3 = new Course("Name1", "description1", BigDecimal.ZERO, LocalDate.now(), LocalDate.now()
                , LocalDate.now(), LocalDate.now(), "logoImagem", StatusCourse.CONCLUSION, LevelCourse.BASIC);
        Course course4 = new Course("Name1", "description1", BigDecimal.ZERO, LocalDate.now(), LocalDate.now()
                , LocalDate.now(), LocalDate.now(), "logoImagem", StatusCourse.CONCLUSION, LevelCourse.BASIC);
        course4.setStatus(false);
        courseRepository.saveAll(Arrays.asList(course1, course2, course3, course4));
    }

    @Test
    @Transactional
    @DisplayName("Buscar todos os curso e checar se tem 3 opcoes")
    public void findAllTest(){
        Page<CourseResponse> page = findAllCourseService.findAll(PageRequest.of(1, 3));
        Assertions.assertEquals(3, page.getSize());
    }
}
