package br.com.marcus.dev.personal.professional.apresentation.services.material.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MaterialFactoryTest {

    @Autowired private MaterialFactory materialFactory;
    @Autowired private PartRepository partRepository;

    @Test
    @Transactional
    @DisplayName("Converter Entidade em Response (Material)")
    public void convertEntityInResponseTest(){
        // Preparacao para o método
        Part part = new Part("Part", Level.EXPERT);
        Language language = new Language("ingles", Level.INTERMEDIARY);
        part.setLanguage(language);
        Material material = new Material("material", part);

        // Executando método
        MaterialResponse materialResponse = materialFactory.convertEntityInResponse(material);

        // Testes Unitários
        Assertions.assertTrue(materialResponse != null);
        Assertions.assertTrue(materialResponse.getPartResponse() != null);
        Assertions.assertEquals("material", materialResponse.getName());
        Assertions.assertEquals("Part", materialResponse.getPartResponse().getName());
        Assertions.assertEquals(Level.EXPERT, materialResponse.getPartResponse().getLevel());
        Assertions.assertEquals("ingles", materialResponse.getPartResponse().getLanguageResponse().getName());
        Assertions.assertEquals(Level.INTERMEDIARY, materialResponse.getPartResponse().getLanguageResponse().getLevel());
    }

    @Test
    @Transactional
    @DisplayName("Converter SaveForm em Entidade (Material)")
    public void convertSaveFormInEntityTest(){
        // Preparacao para o método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        MaterialSaveForm materialSaveForm = new MaterialSaveForm("Material", id);

        // Executando método
        Material material = materialFactory.convertSaveFormInEntity(materialSaveForm);

        // Testes Unitários
        Assertions.assertTrue(material != null);
        Assertions.assertEquals("Material", material.getName());
    }

    @Test
    @Transactional
    @DisplayName("Converter UpdateForm em Entidade (Material)")
    public void convertUpdateFormInEntityTest(){
        // Preparacao para o método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        Part part = new Part(id, "part", Level.ADVANCED);
        partRepository.save(part);
        MaterialUpdateForm materialUpdateForm = new MaterialUpdateForm("Material", UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        Material material = new Material("material", part);

        // Executando método
        Material materialNew = materialFactory.convertUpdateFormInEntity(material, materialUpdateForm);

        // Testes Unitários
        Assertions.assertTrue(materialNew != null);
        Assertions.assertEquals("Material", materialNew.getName());
        Assertions.assertEquals(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370"), materialNew.getPart().getId());
    }
}
