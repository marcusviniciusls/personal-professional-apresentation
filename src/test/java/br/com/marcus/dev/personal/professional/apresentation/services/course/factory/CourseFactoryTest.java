package br.com.marcus.dev.personal.professional.apresentation.services.course.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CourseFactoryTest {

    @Autowired private CourseFactory courseFactory;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    @Test
    @DisplayName("Converter Entidade em Dto (Course)")
    public void convertEntityInDtoTest() {
        // Framework
        Framework spring = new Framework("Spring Boot", "O melhor framework", "");
        spring.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"));
        Framework quarkus = new Framework("Quarkus", "Mais Rápido", "");
        quarkus.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302"));
        // Language Programmer
        LanguageProgrammer java = new LanguageProgrammer("Java SE", "", "");
        LanguageProgrammer csharp = new LanguageProgrammer("C#", "", "");
        java.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303"));
        csharp.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff304"));
        // Course
        Course course = new Course("Frameworks Java", "descricao", BigDecimal.valueOf(50)
                , LocalDate.of(2022,10,1), LocalDate.of(2022,10,8)
                , LocalDate.of(2022,10,2), LocalDate.of(2022,10,8)
                , "logoImagem", StatusCourse.CONCLUSION, LevelCourse.EXPERT);
        course.addListFramework(quarkus);
        course.addListFramework(spring);
        course.addListLanguage(java);
        course.addListLanguage(csharp);

        // Executar Método
        CourseResponse courseResponse = courseFactory.convertEntityInDto(course);

        // Testes Unitários
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

    @Test
    @DisplayName("Converter FormSave em Entidade (Course)")
    public void convertFormSaveInEntityTest() {
        // Course Save Form
        CourseSaveForm courseSaveForm = new CourseSaveForm("Frameworks Java", "descricao", BigDecimal.valueOf(50)
                , LocalDate.of(2022,10,1), LocalDate.of(2022,10,8)
                , LocalDate.of(2022,10,2), LocalDate.of(2022,10,8)
                , "logoImagem", 1, 1);

        // Executar Método
        Course course = courseFactory.convertFormSaveInEntity(courseSaveForm);

        // Teste Unitários
        Assertions.assertTrue(course != null);
        Assertions.assertTrue(course.getId() != null);
        Assertions.assertEquals("descricao", course.getDescription());
        Assertions.assertEquals("Frameworks Java", course.getName());
        Assertions.assertEquals(BigDecimal.valueOf(50), course.getDuration());
        Assertions.assertEquals(LocalDate.of(2022, 10,1), course.getDateInitExpected());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateFinishReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,2), course.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateFinishExpected());
        Assertions.assertEquals(StatusCourse.PROGRESS, course.getStatusCourse());
        Assertions.assertEquals(LevelCourse.INTERMEDIATE, course.getLevelCourse());
    }

    @Test
    @DisplayName("Converter FormUpdate em Entidade (Course)")
    public void convertFormUpdateInEntityTest() {
        // Framework
        Framework spring = new Framework("Spring Boot", "O melhor framework", "");
        spring.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"));
        Framework quarkus = new Framework("Quarkus", "Mais Rápido", "");
        quarkus.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302"));
        // Language Programmer
        LanguageProgrammer java = new LanguageProgrammer("Java SE", "");
        LanguageProgrammer csharp = new LanguageProgrammer("C#", "");
        java.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303"));
        csharp.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff304"));
        // Course
        Course course = new Course("Frameworks Java", "descricao", BigDecimal.valueOf(50)
                , LocalDate.of(2022,10,1), LocalDate.of(2022,10,8)
                , LocalDate.of(2022,10,2), LocalDate.of(2022,10,8)
                , "logoImagem", StatusCourse.CONCLUSION, LevelCourse.EXPERT);
        course.addListFramework(quarkus);
        course.addListFramework(spring);
        course.addListLanguage(java);
        course.addListLanguage(csharp);

        List<ListFramework> listFrameworks = new ArrayList<>();
        listFrameworks.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301")));
        listFrameworks.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302")));
        List<ListLanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303")));
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff304")));
        CourseUpdateForm courseUpdateForm = new CourseUpdateForm("Frameworks Java", "descricao", BigDecimal.valueOf(50)
                , LocalDate.of(2022,10,1), LocalDate.of(2022,10,8)
                , LocalDate.of(2022,10,2), LocalDate.of(2022,10,8)
                , 1, 1, listLanguageProgrammer, listFrameworks);

        course = courseFactory.convertFormUpdateInEntity(courseUpdateForm, course);

        // Testes Unitários
        Assertions.assertTrue(course != null);
        Assertions.assertTrue(course.getId() != null);
        Assertions.assertEquals("descricao", course.getDescription());
        Assertions.assertEquals("Frameworks Java", course.getName());
        Assertions.assertEquals(BigDecimal.valueOf(50), course.getDuration());
        Assertions.assertEquals(LocalDate.of(2022, 10,1), course.getDateInitExpected());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateFinishReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,2), course.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2022, 10,8), course.getDateFinishExpected());
        Assertions.assertEquals(StatusCourse.PROGRESS, course.getStatusCourse());
        Assertions.assertEquals(LevelCourse.INTERMEDIATE, course.getLevelCourse());
        Assertions.assertTrue(course.getListFramework().size() == 2);
        Assertions.assertTrue(course.getListLanguage().size() == 2);
    }
}
