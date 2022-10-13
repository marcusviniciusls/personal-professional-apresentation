package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
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
public class SaveMaterialServiceTest {

    @Autowired private PartRepository partRepository;
    @Autowired private SaveMaterialService saveMaterialService;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private LanguageRepository languageRepository;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Part part = new Part(id,"Part", Level.BEGINNER);
        Language language = new Language("ingles", Level.INTERMEDIARY);
        languageRepository.save(language);
        part.setLanguage(language);
        partRepository.save(part);
        Material material = new Material(id, "material", part);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(material);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Salvar Material com sucesso")
    public void saveTest(){
        // Executando Método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        MaterialSaveForm materialSaveForm = new MaterialSaveForm("teste", id);
        MaterialResponse materialResponse = saveMaterialService.save(materialSaveForm);
        // Testes Unitários
        Assertions.assertTrue(materialResponse != null);
        Assertions.assertTrue(materialResponse.getPartResponse() != null);
        Assertions.assertEquals("material", materialResponse.getName());
        Assertions.assertEquals("Part", materialResponse.getPartResponse().getName());
        Assertions.assertEquals(Level.BEGINNER, materialResponse.getPartResponse().getLevel());
        Assertions.assertEquals("ingles", materialResponse.getPartResponse().getLanguageResponse().getName());
        Assertions.assertEquals(Level.INTERMEDIARY, materialResponse.getPartResponse().getLanguageResponse().getLevel());
    }
}
