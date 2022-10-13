package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateMaterialServiceTest {

    @Autowired private PartRepository partRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private UpdateMaterialService updateMaterialService;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private MaterialRepository materialRepository;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Part part = new Part(id,"Part", Level.ADVANCED);
        Language language = new Language("ingles", Level.INTERMEDIARY);
        languageRepository.save(language);
        part.setLanguage(language);
        partRepository.save(part);
        Material material = new Material(id, "material", part);
        materialRepository.save(material);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(material);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Material com sucesso")
    public void updateTest(){
        // Executando o método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        MaterialUpdateForm materialUpdateForm = new MaterialUpdateForm("teste", id);
        updateMaterialService.update(id, materialUpdateForm);
        Material material = materialRepository.findById(id).get();
        // Testes Unitários
        Assertions.assertTrue(material != null);
        Assertions.assertTrue(material.getPart() != null);
        Assertions.assertEquals("material", material.getName());
        Assertions.assertEquals("Part", material.getPart().getName());
        Assertions.assertEquals(Level.ADVANCED, material.getPart().getLevel());
        Assertions.assertEquals("ingles", material.getPart().getLanguage().getName());
        Assertions.assertEquals(Level.INTERMEDIARY, material.getPart().getLanguage().getLevel());
    }
}
