package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
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

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateFrameworkServiceTest {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private UpdateFrameworkService updateFrameworkService;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework = new Framework(id, "Quarkus", "description", "url");
        frameworkRepository.save(framework);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(framework);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @DisplayName("Atualizar Framework")
    public void updateTest(){
        // Dados de Entradas
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        FrameworkUpdateForm frameworkUpdateForm = new FrameworkUpdateForm("Quarkus", "description");
        // Testes Unitários
        FrameworkResponse response = updateFrameworkService.update(frameworkUpdateForm, id);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Quarkus", response.getName());
        Assertions.assertTrue(response.getId() != null);
        Assertions.assertEquals("description", response.getDescription());
    }
}
