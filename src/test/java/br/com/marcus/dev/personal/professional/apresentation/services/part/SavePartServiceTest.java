package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListMaterial;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SavePartServiceTest {

    @Autowired private LanguageRepository languageRepository;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private SavePartService savePartService;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        // Language
        UUID idLanguage = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Language language = new Language(idLanguage, "Ingles", Level.ADVANCED);
        languageRepository.save(language);

        // Material
        Material material1 = new Material(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"), "Name");
        Material material2 = new Material(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"), "Name");
        Material material3 = new Material(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373"), "Name");
        Material material4 = new Material(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff374"), "Name");
        Material material5 = new Material(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375"), "Name");
        materialRepository.saveAll(Arrays.asList(material1, material2, material3, material4, material5));

        // Dados Mockados
        Part part = new Part("Part", Level.BASIC, language);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(part);
    }

    @Test
    @Transactional
    @DisplayName("Salvar Part")
    public void saveTest(){
        // Preparacao
        PartSaveForm partSaveForm = new PartSaveForm();
        partSaveForm.setName("Part");
        partSaveForm.setLevel(1);
        partSaveForm.setLanguageId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));

        // Lista de Material
        List<ListMaterial> listMaterials = new ArrayList<>();
        listMaterials.add(new ListMaterial(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371")));
        listMaterials.add(new ListMaterial(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372")));
        listMaterials.add(new ListMaterial(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373")));
        listMaterials.add(new ListMaterial(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff374")));
        listMaterials.add(new ListMaterial(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375")));

        partSaveForm.setListMaterial(listMaterials);

        // Executar método
        PartResponse partResponse = savePartService.save(partSaveForm);

        // Testes Unitários
        Assertions.assertTrue(partResponse != null);
        Assertions.assertEquals("Part", partResponse.getName());
        Assertions.assertEquals(Level.BASIC, partResponse.getLevel());
        Assertions.assertEquals("Ingles", partResponse.getLanguageResponse().getName());
        Assertions.assertEquals(Level.ADVANCED, partResponse.getLanguageResponse().getLevel());
    }
}
