package br.com.marcus.dev.personal.professional.apresentation.services.language.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LanguageFactoryTest {

    @Autowired private LanguageFactory languageFactory;

    @Test
    @DisplayName("Converter Entidade em Resposta (Language)")
    public void convertEntityInResponseTest(){
        // Preparacao do ambiente
        Language language = new Language("Ingles", Level.ADVANCED);
        Part part = new Part("Vocabulario", Level.BASIC, language);
        language.addListPart(part);

        // Executando método
        LanguageResponse languageResponse = languageFactory.convertEntityInResponse(language);

        // Testes Unitários
        Assertions.assertTrue(languageResponse != null);
        Assertions.assertEquals("Ingles", languageResponse.getName());
        Assertions.assertEquals(Level.ADVANCED, languageResponse.getLevel());
        Assertions.assertEquals(1, languageResponse.getListPart().size());
    }

    @Test
    @DisplayName("Converter SaveForm em Entidade (Language)")
    public void convertSaveFormInEntityTest(){
        // Preparacao do ambiente
        LanguageSaveForm languageSaveForm = new LanguageSaveForm("Ingles", Level.ADVANCED.getNumber());

        // Executando método
        Language language = languageFactory.convertSaveFormInEntity(languageSaveForm);

        // Testes Unitários
        Assertions.assertTrue(language != null);
        Assertions.assertEquals("Ingles", language.getName());
        Assertions.assertEquals(Level.ADVANCED, language.getLevel());
    }

    @Test
    @DisplayName("Converter UpdateForm em Entidade (Language)")
    public void convertUpdateFormInEntityTest(){
        // Preparacao do ambiente
        Language language = new Language("teste", Level.BASIC);
        LanguageUpdateForm languageUpdateForm = new LanguageUpdateForm("Ingles", Level.ADVANCED.getNumber());

        // Executando método
        language = languageFactory.convertUpdateFormInEntity(language, languageUpdateForm);

        // Testes Unitários
        Assertions.assertTrue(language != null);
        Assertions.assertEquals("Ingles", language.getName());
        Assertions.assertEquals(Level.ADVANCED, language.getLevel());
    }
}
