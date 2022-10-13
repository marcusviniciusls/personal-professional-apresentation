package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
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
public class FindByIdPartServiceTest {

    @Autowired private PartRepository partRepository;
    @Autowired private FindByIdPartService findByIdPartService;
    @Autowired private LanguageRepository languageRepository;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Language language = new Language("name", Level.ADVANCED);
        languageRepository.save(language);
        Part part = new Part(id, "part", Level.ADVANCED);
        part.setLanguage(language);
        partRepository.save(part);

        Part partStatusFalse = new Part(idStatusFalse, "part", Level.ADVANCED);
        partStatusFalse.setLanguage(language);
        partStatusFalse.setStatus(false);
        partRepository.save(partStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Part por id")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        PartResponse partResponse = findByIdPartService.findById(id);
        Assertions.assertTrue(partResponse != null);
        Assertions.assertTrue(partResponse.getLanguageResponse() != null);
        Assertions.assertEquals("part", partResponse.getName());
        Assertions.assertEquals(Level.ADVANCED, partResponse.getLevel());
        Assertions.assertEquals("name", partResponse.getLanguageResponse().getName());
        Assertions.assertEquals(Level.ADVANCED, partResponse.getLanguageResponse().getLevel());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Part por id")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Part part = findByIdPartService.findByIdEntity(id);
        Assertions.assertTrue(part != null);
        Assertions.assertTrue(part.getLanguage() != null);
        Assertions.assertEquals("part", part.getName());
        Assertions.assertEquals(Level.ADVANCED, part.getLevel());
        Assertions.assertEquals("name", part.getLanguage().getName());
        Assertions.assertEquals(Level.ADVANCED, part.getLanguage().getLevel());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Part por id - Status False")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Part por id - Status False")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Part por id - Not Found")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Part por id - Not Found")
    public void findByIdEntityNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdPartService.findByIdEntity(id));
    }
}
