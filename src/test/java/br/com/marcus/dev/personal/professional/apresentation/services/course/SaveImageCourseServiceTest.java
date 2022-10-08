package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveImageCourseServiceTest {

    @Autowired private SaveImageCourseService saveImageCourseService;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private CourseRepository courseRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SendFileAwsS3 sendFileAwsS3;

    @Test
    @DisplayName("Salvar a imagem do curso com sucesso")
    public void saveImageCourseTest() throws URISyntaxException {
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
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);

        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(course);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));

        // Executar Teste
        saveImageCourseService.saveImageCourse(multipartFile, UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        Optional<Course> courseOptional = courseRepository.findById(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        Assertions.assertEquals("caminho/imagem.png", courseOptional.get().getLogoImage());
    }

    @Test
    @DisplayName("Salvar a imagem do curso com error")
    public void saveImageCourseWirhErrorTest() throws URISyntaxException {
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
                , "teste", StatusCourse.CONCLUSION, LevelCourse.EXPERT);
        course.addListFramework(quarkus);
        course.addListFramework(spring);
        course.addListLanguage(java);
        course.addListLanguage(csharp);
        course.setId(UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001"));
        courseRepository.save(course);
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);

        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(course);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));

        // Executar Teste
        Assertions.assertThrows(FileException.class, () -> saveImageCourseService.saveImageCourse(multipartFile, UUID.fromString("db260da4-01fb-48f0-aec4-d7f9db2ff001")));
    }
}
