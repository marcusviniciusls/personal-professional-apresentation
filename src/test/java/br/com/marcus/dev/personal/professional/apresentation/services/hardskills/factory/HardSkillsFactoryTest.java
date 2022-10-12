package br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.UpdateHardSkillsService;
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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HardSkillsFactoryTest {

    @Autowired private HardSkillsFactory hardSkillsFactory;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private HardSkillsRepository hardSkillsRepository;

    @Test
    @DisplayName("Converter Entidade em Resposta (HardSkills)")
    public void convertEntityInResponseTest(){
        // Preparacao de Entrada
        Framework framework1 = new Framework("Quarkus", "description", "urlImage");
        Framework framework2 = new Framework("Spring Boot", "description", "urlImage");
        LanguageProgrammer language1 = new LanguageProgrammer("Java", "");
        LanguageProgrammer language2 = new LanguageProgrammer("C#", "");
        LanguageProgrammer language3 = new LanguageProgrammer("HTML", "");

        // Dados Mockados
        HardSkills hardSkills = new HardSkills("Java Programmer", "", Level.ADVANCED);
        hardSkills.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370"));
        hardSkills.addListLanguageProgrammer(language1);
        hardSkills.addListLanguageProgrammer(language2);
        hardSkills.addListLanguageProgrammer(language3);
        hardSkills.addListFramework(framework1);
        hardSkills.addListFramework(framework2);

        // Executar método
        HardSkillsResponse hardSkillsResponse = hardSkillsFactory.convertEntityInResponse(hardSkills);

        // Testes Unitários
        Assertions.assertTrue(hardSkillsResponse != null);
        Assertions.assertEquals("Java Programmer", hardSkillsResponse.getName());
        Assertions.assertEquals("", hardSkillsResponse.getDescription());
        Assertions.assertEquals(Level.ADVANCED, hardSkillsResponse.getLevel());
        Assertions.assertTrue(hardSkillsResponse.getId() != null);
        Assertions.assertEquals(2, hardSkillsResponse.getListFrameworkResponse().size());
        Assertions.assertEquals(3, hardSkillsResponse.getListLanguageProgrammerResponse().size());
    }

    @Test
    @DisplayName("Converter Request Save em Entidade (HardSkills)")
    public void convertRequestSaveInEntityTest(){
        // Preparacao de Entrada
        UUID idFramework1 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        UUID idFramework2 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        UUID idLanguageProgrammer1 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        UUID idLanguageProgrammer2 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff374");
        UUID idLanguageProgrammer3 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Framework framework1 = new Framework(idFramework1,"Quarkus", "description", "urlImage");
        Framework framework2 = new Framework(idFramework2, "Spring Boot", "description", "urlImage");
        LanguageProgrammer language1 = new LanguageProgrammer(idLanguageProgrammer1,"Java", "");
        LanguageProgrammer language2 = new LanguageProgrammer(idLanguageProgrammer2, "C#", "");
        LanguageProgrammer language3 = new LanguageProgrammer(idLanguageProgrammer3, "HTML", "");
        frameworkRepository.saveAll(Arrays.asList(framework1, framework2));
        languageProgrammerRepository.saveAll(Arrays.asList(language1, language2, language3));
        // Framework
        ListFramework listFramework1 = new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        ListFramework listFramework2 = new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"));
        List<ListFramework> listIdFramework = new ArrayList<>();
        listIdFramework.add(listFramework1);
        listIdFramework.add(listFramework2);
        // LanguageProgrammer
        ListLanguageProgrammer listLanguageProgrammer1 = new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373"));
        ListLanguageProgrammer listLanguageProgrammer2 = new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff374"));
        ListLanguageProgrammer listLanguageProgrammer3 = new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375"));
        List<ListLanguageProgrammer> listIdLanguageProgrammer = new ArrayList<>();
        listIdLanguageProgrammer.add(listLanguageProgrammer1);
        listIdLanguageProgrammer.add(listLanguageProgrammer2);
        listIdLanguageProgrammer.add(listLanguageProgrammer3);

        HardSkillsSaveForm hardSkillsSaveForm = new HardSkillsSaveForm("Quarkus", "", 1, listIdFramework, listIdLanguageProgrammer);
        // Execucao dos testes
        HardSkills hardSkills = hardSkillsFactory.convertRequestSaveInEntity(hardSkillsSaveForm);
        // Testes Unitários
        Assertions.assertTrue(hardSkills != null);
        Assertions.assertEquals("Quarkus", hardSkills.getName());
        Assertions.assertEquals("", hardSkills.getDescription());
        Assertions.assertEquals(Level.BASIC, hardSkills.getLevel());
        Assertions.assertTrue(hardSkills.getId() != null);
        Assertions.assertEquals(2, hardSkills.getListFramework().size());
        Assertions.assertEquals(3, hardSkills.getListLanguage().size());
    }

    @Test
    @DisplayName("Converter Request Update em Entidade (HardSkills)")
    public void convertUpdateFormInEntity(){
        // Preparacao de Entrada
        UUID idFramework1 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        UUID idFramework2 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        UUID idLanguageProgrammer1 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        UUID idLanguageProgrammer2 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff374");
        UUID idLanguageProgrammer3 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Framework framework1 = new Framework(idFramework1,"Quarkus", "description", "urlImage");
        Framework framework2 = new Framework(idFramework2, "Spring Boot", "description", "urlImage");
        LanguageProgrammer language1 = new LanguageProgrammer(idLanguageProgrammer1,"Java", "");
        LanguageProgrammer language2 = new LanguageProgrammer(idLanguageProgrammer2, "C#", "");
        LanguageProgrammer language3 = new LanguageProgrammer(idLanguageProgrammer3, "HTML", "");
        frameworkRepository.saveAll(Arrays.asList(framework1, framework2));
        languageProgrammerRepository.saveAll(Arrays.asList(language1, language2, language3));
        // Framework
        ListFramework listFramework1 = new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        ListFramework listFramework2 = new ListFramework(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"));
        List<ListFramework> listIdFramework = new ArrayList<>();
        listIdFramework.add(listFramework1);
        listIdFramework.add(listFramework2);
        // LanguageProgrammer
        ListLanguageProgrammer listLanguageProgrammer1 = new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373"));
        ListLanguageProgrammer listLanguageProgrammer2 = new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff374"));
        ListLanguageProgrammer listLanguageProgrammer3 = new ListLanguageProgrammer(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375"));
        List<ListLanguageProgrammer> listIdLanguageProgrammer = new ArrayList<>();
        listIdLanguageProgrammer.add(listLanguageProgrammer1);
        listIdLanguageProgrammer.add(listLanguageProgrammer2);
        listIdLanguageProgrammer.add(listLanguageProgrammer3);

        HardSkills hardSkills = new HardSkills("Java Programmer", "teste", Level.ADVANCED);
        hardSkills.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370"));
        hardSkills.addListLanguageProgrammer(language1);
        hardSkills.addListLanguageProgrammer(language2);
        hardSkills.addListLanguageProgrammer(language3);
        hardSkills.addListFramework(framework1);
        hardSkills.addListFramework(framework2);
        hardSkillsRepository.save(hardSkills);

        HardSkillsUpdateForm hardSkillsUpdateForm = new HardSkillsUpdateForm("Quarkus", "teste", 1, listIdFramework, listIdLanguageProgrammer);
        // Execucao dos testes
        HardSkills hardSkillsNew = hardSkillsFactory.convertUpdateFormInEntity(hardSkillsUpdateForm, hardSkills);
        // Testes Unitários
        Assertions.assertTrue(hardSkillsNew != null);
        Assertions.assertEquals("Quarkus", hardSkillsNew.getName());
        Assertions.assertEquals("teste", hardSkillsNew.getDescription());
        Assertions.assertEquals(Level.BASIC, hardSkillsNew.getLevel());
        Assertions.assertTrue(hardSkillsNew.getId() != null);
        Assertions.assertEquals(2, hardSkillsNew.getListFramework().size());
        Assertions.assertEquals(3, hardSkillsNew.getListLanguage().size());
    }
}
