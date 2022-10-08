package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
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

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateCourseServiceTest {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private UpdateCourseService updateCourseService;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @Transactional
    @DisplayName("Atualizar com sucesso o Curso")
    public void updateTest() {
        // Dados de Entrada
        Framework spring = new Framework("Spring Boot", "O melhor framework", "");
        spring.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"));
        Framework quarkus = new Framework("Quarkus", "Mais Rápido", "");
        quarkus.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302"));
        frameworkRepository.saveAll(Arrays.asList(spring, quarkus));
        // Language Programmer
        LanguageProgrammer java = new LanguageProgrammer("Java SE", "");
        LanguageProgrammer csharp = new LanguageProgrammer("C#", "");
        java.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303"));
        csharp.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff304"));
        languageProgrammerRepository.saveAll(Arrays.asList(java, csharp));

        Course course = new Course("Frameworks Java", "descricao", BigDecimal.valueOf(50)
                , LocalDate.of(2022,10,1), LocalDate.of(2022,10,8)
                , LocalDate.of(2022,10,2), LocalDate.of(2022,10,8)
                , "", StatusCourse.CONCLUSION, LevelCourse.EXPERT);
        course.addListFramework(quarkus);
        course.addListFramework(spring);
        course.addListLanguage(java);
        course.addListLanguage(csharp);
        course.setId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        courseRepository.save(course);

        List<ListFramework> listFrameworks = new ArrayList<>();
        listFrameworks.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301")));
        listFrameworks.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302")));
        List<ListLanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303")));
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff304")));
        CourseUpdateForm request = new CourseUpdateForm("Frameworks Java", "descricao", BigDecimal.valueOf(50)
                , LocalDate.of(2021,10,1), LocalDate.of(2021,10,8)
                , LocalDate.of(2021,10,2), LocalDate.of(2021,10,8)
                , 2, 3, listLanguageProgrammer, listFrameworks);

        // Dados Mockados
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(course);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);

        // Executar método
        CourseResponse courseResponse = updateCourseService.update(request, UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));

        // Teste Unitários
        Assertions.assertTrue(courseResponse != null);
        Assertions.assertTrue(courseResponse.getId() != null);
        Assertions.assertEquals("descricao", courseResponse.getDescription());
        Assertions.assertEquals("Frameworks Java", courseResponse.getName());
        Assertions.assertEquals(BigDecimal.valueOf(50), courseResponse.getDuration());
        Assertions.assertEquals(LocalDate.of(2022, 10,1), courseResponse.getDateInitExpected());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), courseResponse.getDateFinishReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,2), courseResponse.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), courseResponse.getDateFinishExpected());
        Assertions.assertEquals(StatusCourse.CONCLUSION, courseResponse.getStatusCourse());
        Assertions.assertEquals(LevelCourse.EXPERT, courseResponse.getLevelCourse());
        Assertions.assertTrue(courseResponse.getListFramework().size() == 2);
        Assertions.assertTrue(courseResponse.getListLanguageProgrammerResponse().size() == 2);
    }
}
