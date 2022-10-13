package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteLanguageServiceTest {

    @Autowired private LanguageRepository languageRepository;
    @Autowired private DeleteLanguageService deleteLanguageService;

    @BeforeEach
    public void setupInit(){
        // Preparacao do ambiente
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Language language = new Language(id, "Ingles", Level.ADVANCED);
        languageRepository.save(language);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Language")
    public void deleteTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        // Executar método
        deleteLanguageService.delete(id);
        // Testes Unitários
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        Assertions.assertTrue(optionalLanguage.isEmpty());
    }
}
