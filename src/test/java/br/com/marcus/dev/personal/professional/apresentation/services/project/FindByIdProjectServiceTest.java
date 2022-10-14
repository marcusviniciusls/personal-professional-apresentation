package br.com.marcus.dev.personal.professional.apresentation.services.project;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdProjectServiceTest {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private FindByIdProjectService findByIdProjectService;

    @BeforeEach
    public void setupInit(){
        // Language Programmer
        LanguageProgrammer java = new LanguageProgrammer("Java", "");
        LanguageProgrammer html = new LanguageProgrammer("HTML", "");
        List<LanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        listLanguageProgrammer.add(java);
        listLanguageProgrammer.add(html);
        languageProgrammerRepository.saveAll(Arrays.asList(java, html));
        // Framework
        Framework spring = new Framework("Spring", "", "urlImage");
        Framework angular = new Framework("Angular", "", "urlImage");
        List<Framework> listFramework = new ArrayList<>();
        listFramework.add(spring);
        listFramework.add(angular);
        frameworkRepository.saveAll(Arrays.asList(spring, angular));
        // Project
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Project project = new Project("API REST JAVA + ANGULAR", "DESCRIPTION",
                "linkGitHub", "linkYoutube", listLanguageProgrammer, listFramework);
        project.setId(id);
        projectRepository.save(project);

        // Project Status False
        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Project projectStatusFalse = new Project("API REST JAVA + ANGULAR", "DESCRIPTION",
                "linkGitHub", "linkYoutube", listLanguageProgrammer, listFramework);
        projectStatusFalse.setId(idStatusFalse);
        projectStatusFalse.setStatus(false);
        projectRepository.save(projectStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Project por ID")
    public void findByIdTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProjectResponse response = findByIdProjectService.findById(id);
        // Teste Unitário
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("API REST JAVA + ANGULAR", response.getTitle());
        Assertions.assertEquals("DESCRIPTION", response.getDescription());
        Assertions.assertEquals("linkGitHub", response.getLinkGitHub());
        Assertions.assertEquals("linkYoutube", response.getLinkYoutube());
        Assertions.assertEquals(2, response.getListFrameworkResponse().size());
        Assertions.assertEquals(2, response.getListLanguageProgrammerResponse().size());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Project por ID")
    public void findByIdEntityTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Project project = findByIdProjectService.findByIdEntity(id);
        // Teste Unitário
        Assertions.assertTrue(project != null);
        Assertions.assertEquals("API REST JAVA + ANGULAR", project.getTitle());
        Assertions.assertEquals("DESCRIPTION", project.getDescription());
        Assertions.assertEquals("linkGitHub", project.getLinkGitHub());
        Assertions.assertEquals("linkYoutube", project.getLinkYoutube());
        Assertions.assertEquals(2, project.getListFramework().size());
        Assertions.assertEquals(2, project.getListLanguageProgrammer().size());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Project por ID - Status False")
    public void findByIdStatusFalseTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProjectService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Project por ID - Status False")
    public void findByIdEntityStatusFalseTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProjectService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Project por ID - NotFound")
    public void findByIdNotFoundTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff100");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProjectService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Project por ID - NotFound")
    public void findByIdEntityNotFoundTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff100");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProjectService.findByIdEntity(id));
    }
}
