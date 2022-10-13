package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
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

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveLanguageServiceTest {

    @Autowired private SaveLanguageService saveLanguageService;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @Transactional
    @DisplayName("Salvar Language")
    public void saveTest(){
        // Dados Mockados
        Language language = new Language("Ingles", Level.ADVANCED);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(language);
        // Execucao dos testes
        LanguageSaveForm languageSaveForm = new LanguageSaveForm("Ingles", Level.ADVANCED.getNumber());
        LanguageResponse response = saveLanguageService.save(languageSaveForm);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Ingles", response.getName());
        Assertions.assertEquals(Level.ADVANCED, response.getLevel());
    }
}
