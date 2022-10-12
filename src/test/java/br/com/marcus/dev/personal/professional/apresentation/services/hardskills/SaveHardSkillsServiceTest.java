package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.checkerframework.checker.units.qual.A;
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
public class SaveHardSkillsServiceTest {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private SaveHardSkillsService saveHardSkillsService;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SaveActivitiesService saveActivitiesService;

    @BeforeEach
    public void setupInit(){
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

        // Dados Mockados
        HardSkills hardSkills = new HardSkills("Java Programmer", "", Level.ADVANCED);
        hardSkills.addListLanguageProgrammer(language1);
        hardSkills.addListLanguageProgrammer(language2);
        hardSkills.addListLanguageProgrammer(language3);
        hardSkills.addListFramework(framework1);
        hardSkills.addListFramework(framework2);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(hardSkills);
        BDDMockito.given(saveActivitiesService.saveMovementHardSkills(Mockito.any(HardSkills.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Salvar HardSkills")
    public void saveTest(){
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
        HardSkillsResponse hardSkillsResponse = saveHardSkillsService.save(hardSkillsSaveForm);
        // Testes Unitários
        Assertions.assertTrue(hardSkillsResponse != null);
        Assertions.assertEquals("Java Programmer", hardSkillsResponse.getName());
        Assertions.assertEquals("", hardSkillsResponse.getDescription());
        Assertions.assertEquals(Level.ADVANCED, hardSkillsResponse.getLevel());
        Assertions.assertTrue(hardSkillsResponse.getId() != null);
        Assertions.assertEquals(2, hardSkillsResponse.getListFrameworkResponse().size());
        Assertions.assertEquals(3, hardSkillsResponse.getListLanguageProgrammerResponse().size());
    }
}
