package br.com.marcus.dev.personal.professional.apresentation.services.project;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
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
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteProjectServiceTest {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private DeleteProjectService deleteProjectService;

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
    }

    @Test
    @Transactional
    @DisplayName("Apagar Project")
    public void deleteTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deleteProjectService.delete(id);
        // Teste Unitário
        Optional<Project> response = projectRepository.findById(id);
        Assertions.assertTrue(response.isEmpty());
    }
}
