package br.com.marcus.dev.personal.professional.apresentation.services.softskills.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory.SoftSkillsFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SoftSkillsFactoryTest {

    @Autowired private SoftSkillsFactory softSkillsFactory;

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Dto (SoftSkills)")
    public void convertEntityInDtoTest(){
        // Preparacao e execucao do método
        SoftSkills softSkills = new SoftSkills("Boa comunicacao", true);
        SoftSkillsResponse response = softSkillsFactory.convertEntityInDto(softSkills);
        // Teste Unitário
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Boa comunicacao", response.getName());
        Assertions.assertEquals(true, response.isStatusHas());
    }

    @Test
    @Transactional
    @DisplayName("Converter FormSave em Entity (SoftSkills)")
    public void convertFormInEntityToSaveTest(){
        // Preparacao e execucao do método
        SoftSkillsFormSave softSkillsFormSave = new SoftSkillsFormSave("Boa comunicacao");
        SoftSkills softSkills = softSkillsFactory.convertFormInEntityToSave(softSkillsFormSave);
        // Teste Unitário
        Assertions.assertTrue(softSkills != null);
        Assertions.assertEquals("Boa comunicacao", softSkills.getName());
        Assertions.assertEquals(true, softSkills.isStatusHas());
    }

    @Test
    @Transactional
    @DisplayName("Converter FormUpdate em Entity (SoftSkills)")
    public void convertFormUpdateToEntityTest(){
        // Preparacao e execucao do método
        SoftSkills softSkills = new SoftSkills("teste", false);
        SoftSkillsFormUpdate softSkillsFormUpdate = new SoftSkillsFormUpdate("Boa Comunicacao");
        softSkills = softSkillsFactory.convertFormUpdateToEntity(softSkillsFormUpdate, softSkills);
        // Teste Unitário
        Assertions.assertTrue(softSkills != null);
        Assertions.assertEquals("Boa Comunicacao", softSkills.getName());
        Assertions.assertEquals(false, softSkills.isStatusHas());
    }
}
