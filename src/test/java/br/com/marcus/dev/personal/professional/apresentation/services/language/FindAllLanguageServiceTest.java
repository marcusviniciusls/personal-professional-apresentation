package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllLanguageServiceTest {

    @Autowired private LanguageRepository languageRepository;
    @Autowired private FindAllLanguageService findAllLanguageService;
    @Autowired private PartRepository partRepository;

    @BeforeEach
    public void setupInit(){
        // Preparacao do ambiente
        Language language = new Language("Ingles", Level.ADVANCED);
        languageRepository.save(language);
        Part part = new Part("Vocabulario", Level.BASIC, language);
        partRepository.save(part);
    }

    @Test
    @Transactional
    @DisplayName("Buscar todas as Languages")
    public void findAllTest(){
        // Executar método
        Page<LanguageResponse> request = findAllLanguageService.findAll(PageRequest.of(1,1));
        // Teste unitário
        Assertions.assertEquals(1, request.getSize());
    }
}
