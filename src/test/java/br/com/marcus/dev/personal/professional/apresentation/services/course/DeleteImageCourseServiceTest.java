package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
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
public class DeleteImageCourseServiceTest {

    @Autowired private CourseRepository courseRepository;
    @Autowired private DeleteImageCourseService deleteImageCourseService;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        Course course = new Course(UUID.fromString("zb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        course.setLogoImage("caminho/teste.png");
        Course courseReturn = new Course(UUID.fromString("zb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        courseReturn.setLogoImage("");
        courseRepository.save(course);
        courseRepository.save(courseReturn);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(courseReturn);
    }

    @Test
    @DisplayName("Apagar um course com sucesso")
    public void DeleteImageCourseService(){
        // Executar método
        UUID id = UUID.fromString("zb260da4-01fb-48f0-aec4-d7f9db2ff702");
        deleteImageCourseService.deleteImageS3(id);

        // Teste
        Optional<Course> optionalCourse = courseRepository.findById(id);
        Assertions.assertEquals("", optionalCourse.get().getLogoImage());
    }
}
