package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
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
public class FindByIdMaterialServiceTest {

    @Autowired private MaterialRepository materialRepository;
    @Autowired private PartRepository partRepository;
    @Autowired private FindByIdMaterialService findByIdMaterialService;
    @Autowired private LanguageRepository languageRepository;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Language language = new Language("ingles", Level.BASIC);
        languageRepository.save(language);
        Part part = new Part("Part", Level.ADVANCED);
        part.setLanguage(language);
        partRepository.save(part);
        Material material = new Material(id, "material", part);
        materialRepository.save(material);

        Material materialStatusFalse = new Material(idStatusFalse, "material", part);
        materialStatusFalse.setStatus(false);
        materialRepository.save(materialStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Material por Id com sucesso")
    public void findByIdTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        MaterialResponse materialResponse = findByIdMaterialService.findById(id);
        // Testes Unitários
        Assertions.assertTrue(materialResponse != null);
        Assertions.assertTrue(materialResponse.getPartResponse() != null);
        Assertions.assertEquals("material", materialResponse.getName());
        Assertions.assertEquals("Part", materialResponse.getPartResponse().getName());
        Assertions.assertEquals(Level.ADVANCED, materialResponse.getPartResponse().getLevel());
        Assertions.assertEquals("ingles", materialResponse.getPartResponse().getLanguageResponse().getName());
        Assertions.assertEquals(Level.BASIC, materialResponse.getPartResponse().getLanguageResponse().getLevel());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Material por Id com sucesso")
    public void findByIdEntityTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        Material material = findByIdMaterialService.findByIdEntity(id);
        // Testes Unitários
        Assertions.assertTrue(material != null);
        Assertions.assertTrue(material.getPart() != null);
        Assertions.assertEquals("material", material.getName());
        Assertions.assertEquals("Part", material.getPart().getName());
        Assertions.assertEquals(Level.ADVANCED, material.getPart().getLevel());
        Assertions.assertEquals("ingles", material.getPart().getLanguage().getName());
        Assertions.assertEquals(Level.BASIC, material.getPart().getLanguage().getLevel());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Material por Id - Status False")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdMaterialService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Material por Id - Status False")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdMaterialService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Material por Id - NotFound")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff354");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdMaterialService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Material por Id - NotFound")
    public void findByIdEntityNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff321");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdMaterialService.findByIdEntity(id));
    }
}
