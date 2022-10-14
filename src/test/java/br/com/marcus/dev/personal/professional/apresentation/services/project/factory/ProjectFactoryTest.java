package br.com.marcus.dev.personal.professional.apresentation.services.project.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
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
public class ProjectFactoryTest {

    @Autowired private ProjectFactory projectFactory;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private FrameworkRepository frameworkRepository;

    @BeforeEach
    public void setupInit(){
        // Language Programmer
        LanguageProgrammer java = new LanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff360"), "Java", "");
        LanguageProgrammer html = new LanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff361"), "HTML", "");
        List<LanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        listLanguageProgrammer.add(java);
        listLanguageProgrammer.add(html);
        languageProgrammerRepository.saveAll(Arrays.asList(java, html));
        // Framework
        Framework spring = new Framework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff363"), "Spring", "", "urlImage");
        Framework angular = new Framework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff364"), "Angular", "", "urlImage");
        List<Framework> listFramework = new ArrayList<>();
        listFramework.add(spring);
        listFramework.add(angular);
        frameworkRepository.saveAll(Arrays.asList(spring, angular));
    }

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Resposta (Project)")
    public void convertEntityInResponseTest(){
        // Language Programmer
        LanguageProgrammer java = new LanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff360"), "Java", "");
        LanguageProgrammer html = new LanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff361"), "HTML", "");
        List<LanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        listLanguageProgrammer.add(java);
        listLanguageProgrammer.add(html);
        // Framework
        Framework spring = new Framework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff363"), "Spring", "", "urlImage");
        Framework angular = new Framework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff364"), "Angular", "", "urlImage");
        List<Framework> listFramework = new ArrayList<>();
        listFramework.add(spring);
        listFramework.add(angular);
        // Project
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Project project = new Project("API REST JAVA + ANGULAR", "DESCRIPTION",
                "linkGitHub", "linkYoutube", listLanguageProgrammer, listFramework);
        project.setId(id);
        // Executar teste
        ProjectResponse response = projectFactory.convertEntityInResponse(project);

        // Testes Unitários
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
    @DisplayName("Converter SaveForm em Entidade (Project)")
    public void convertSaveFormInEntityTest(){
        // Executando método
        List<ListLanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        List<ListFramework> listFramework = new ArrayList<>();
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff360")));
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff361")));
        listFramework.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff363")));
        listFramework.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff364")));
        ProjectSaveForm projectSaveForm = new ProjectSaveForm("API REST JAVA + ANGULAR", "DESCRIPTION",
                "linkGitHub", "linkYoutube", listLanguageProgrammer, listFramework);

        Project project = projectFactory.convertSaveFormInEntity(projectSaveForm);

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
    @DisplayName("Converter UpdateForm em Entidade (Project)")
    public void convertUpdateFormInEntityTest(){
        // Executando método
        List<ListLanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        List<ListFramework> listFramework = new ArrayList<>();
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff360")));
        listLanguageProgrammer.add(new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff361")));
        listFramework.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff363")));
        listFramework.add(new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff364")));
        ProjectUpdateForm projectUpdateForm = new ProjectUpdateForm("API REST JAVA + ANGULAR", "DESCRIPTION",
                "linkGitHub", "linkYoutube", listLanguageProgrammer, listFramework);

        Project project = new Project("teste", "teste", "teste", "teste");

        project = projectFactory.convertUpdateFormInEntity(projectUpdateForm, project);

        // Teste Unitário
        Assertions.assertTrue(project != null);
        Assertions.assertEquals("API REST JAVA + ANGULAR", project.getTitle());
        Assertions.assertEquals("DESCRIPTION", project.getDescription());
        Assertions.assertEquals("linkGitHub", project.getLinkGitHub());
        Assertions.assertEquals("linkYoutube", project.getLinkYoutube());
        Assertions.assertEquals(2, project.getListFramework().size());
        Assertions.assertEquals(2, project.getListLanguageProgrammer().size());
    }
}
