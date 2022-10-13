package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerSaveFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerUpdateFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LanguageProgrammerFactoryTest {

    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;

    @Test
    @DisplayName("Converter Entidade em Resposta (LanguageProgrammer)")
    public void convertEntityInResponseTest(){
        LanguageProgrammer languageProgrammer = new LanguageProgrammer("Java", "Teste");
        LanguageProgrammerResponse response = languageProgrammerFactory.convertEntityInResponse(languageProgrammer);
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Java", response.getName());
        Assertions.assertEquals("Teste", response.getDescription());
    }

    @Test
    @DisplayName("Converter UpdateSave em Entidade (LanguageProgrammer)")
    public void convertUpdateSaveInEntityTest(){
        LanguageProgrammerSaveFrom languageProgrammerSaveFrom = new LanguageProgrammerSaveFrom("Java", "Teste");
        LanguageProgrammer entidade = languageProgrammerFactory.convertUpdateSaveInEntity(languageProgrammerSaveFrom);
        Assertions.assertTrue(entidade != null);
        Assertions.assertEquals("Java", entidade.getName());
        Assertions.assertEquals("Teste", entidade.getDescription());
    }

    @Test
    @DisplayName("Converter UpdateForm em Entidade (LanguageProgrammer)")
    public void convertUpdateFormInEntityTest(){
        LanguageProgrammer languageProgrammer = new LanguageProgrammer("Java", "Teste");
        LanguageProgrammerUpdateFrom update = new LanguageProgrammerUpdateFrom("Java", "Teste");
        LanguageProgrammer entidade = languageProgrammerFactory.convertUpdateFormInEntity(languageProgrammer, update);
        Assertions.assertTrue(entidade != null);
        Assertions.assertEquals("Java", entidade.getName());
        Assertions.assertEquals("Teste", entidade.getDescription());
    }
}
