package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveFrameworkServiceTest {

    @Autowired private SaveFrameworkService saveFrameworkService;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private FrameworkRepository frameworkRepository;

    @Test
    @DisplayName("Salvar Framework")
    public void saveTest(){
        // Dados Mockado
        Framework framework = new Framework("Quarkus", "description", "urlImage");
        frameworkRepository.save(framework);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(framework);
        // Dados de Entrada
        FrameworkSaveForm frameworkSaveForm = new FrameworkSaveForm("Quarkus", "description");
        // Executar o método
        FrameworkResponse response = saveFrameworkService.save(frameworkSaveForm);
        // Teste Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Quarkus", response.getName());
        Assertions.assertTrue(response.getId() != null);
        Assertions.assertEquals("description", response.getDescription());
    }
}
