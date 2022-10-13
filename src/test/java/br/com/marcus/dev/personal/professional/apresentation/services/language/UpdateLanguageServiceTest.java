package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateLanguageServiceTest {

    @MockBean private CenterEntityService centerEntityService;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private UpdateLanguageService updateLanguageService;

    @BeforeEach
    public void setupInit(){
        // Preparacao do ambiente
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        Language language = new Language(id,"Ingles", Level.ADVANCED);
        languageRepository.save(language);
        // Dados Mockados
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(language);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Language")
    public void updateTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        LanguageUpdateForm languageUpdateForm = new LanguageUpdateForm("teste", 1);
        // Executar método
        updateLanguageService.update(id, languageUpdateForm);
        Language language = languageRepository.findById(id).get();
        // Testes Unitários
        Assertions.assertTrue(language != null);
        Assertions.assertEquals("Ingles", language.getName());
        Assertions.assertEquals(Level.ADVANCED, language.getLevel());
    }
}
