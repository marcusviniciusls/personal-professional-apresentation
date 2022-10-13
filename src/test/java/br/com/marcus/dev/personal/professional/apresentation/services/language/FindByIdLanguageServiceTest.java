package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdLanguageServiceTest {

    @Autowired private LanguageRepository languageRepository;
    @Autowired private PartRepository partRepository;
    @Autowired private FindByIdLanguageService findByIdLanguageService;
    @Autowired private MaterialRepository materialRepository;

    @BeforeEach
    public void setupInit(){
        // Preparacao do ambiente
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Language language = new Language(id,"Ingles", Level.ADVANCED);
        languageRepository.save(language);
        Part part = new Part("Vocabulario", Level.BASIC, language);
        partRepository.save(part);
        Material material = new Material("teste", part);
        materialRepository.save(material);

        // Status False
        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Language languageStatusFalse = new Language(idStatusFalse,"Ingles", Level.ADVANCED);
        languageStatusFalse.setStatus(false);
        languageRepository.save(languageStatusFalse);
        Part partStatusFalse = new Part("Vocabulario", Level.BASIC, language);
        partRepository.save(partStatusFalse);
        Material materialStatusFalse = new Material("teste", part);
        materialRepository.save(materialStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Language por Id")
    public void findByIdTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        LanguageResponse languageResponse = findByIdLanguageService.findById(id);
        // Testes Unitários
        Assertions.assertTrue(languageResponse != null);
        Assertions.assertEquals("Ingles", languageResponse.getName());
        Assertions.assertTrue(languageResponse.getListPart() != null);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Language por Id")
    public void findByIdEntityTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Language language = findByIdLanguageService.findByIdEntity(id);
        // Testes Unitários
        Assertions.assertTrue(language != null);
        Assertions.assertEquals("Ingles", language.getName());
        Assertions.assertTrue(language.getListPart() != null);
        Assertions.assertEquals(id, language.getId());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Language por Id - Status False")
    public void findByIdStatusFalseTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Language por Id - Status False")
    public void findByIdEntityStatusFalseTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Language por Id - Not Found")
    public void findByIdNotFoundTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Language por Id - Not Found")
    public void findByIdEntityNotFoundTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageService.findByIdEntity(id));
    }
}
