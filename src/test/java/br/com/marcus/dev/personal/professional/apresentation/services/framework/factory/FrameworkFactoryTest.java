package br.com.marcus.dev.personal.professional.apresentation.services.framework.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FrameworkFactoryTest {

    @Autowired private FrameworkFactory frameworkFactory;

    @Test
    @DisplayName("Converter Entidade em Dto (Framework)")
    public void convertEntityInDtoTest(){
        // Dados de Entrada
        Framework framework = new Framework("name", "description", "urlImage");
        // Executar método
        FrameworkResponse response = frameworkFactory.convertEntityInDto(framework);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("name", response.getName());
        Assertions.assertTrue(response.getId() != null);
        Assertions.assertEquals("description", response.getDescription());
    }

    @Test
    @DisplayName("Converter FormSave em Entidade (Framework)")
    public void convertFormSaveInEntityTest(){
        // Dados de Entrada
        FrameworkSaveForm save = new FrameworkSaveForm("name", "description");
        // Executar método
        Framework entidade = frameworkFactory.convertFormSaveInEntity(save);
        // Testes Unitários
        Assertions.assertTrue(entidade != null);
        Assertions.assertEquals("name", entidade.getName());
        Assertions.assertTrue(entidade.getId() != null);
        Assertions.assertEquals("description", entidade.getDescription());
    }

    @Test
    @DisplayName("Converter Entidade em Resposta (Framework)")
    public void convertEntityInResponseTest(){
        // Dados de Entrada
        Framework framework = new Framework("name", "description", "url");
        // Executar método
        FrameworkResponse resposne = frameworkFactory.convertEntityInResponse(framework);
        // Testes Unitários
        Assertions.assertTrue(resposne != null);
        Assertions.assertEquals("name", resposne.getName());
        Assertions.assertTrue(resposne.getId() != null);
        Assertions.assertEquals("description", resposne.getDescription());
    }

    @Test
    @DisplayName("Converter Update Form em Entidade (Framework)")
    public void convertUpdateFormInEntityTest(){
        // Dados de Entrada
        Framework framework = new Framework("name", "description", "url");
        FrameworkUpdateForm refresh = new FrameworkUpdateForm("name1", "description1");
        // Executar método
        Framework entidade = frameworkFactory.convertUpdateFormInEntity(refresh, framework);
        // Testes Unitários
        Assertions.assertTrue(entidade != null);
        Assertions.assertEquals("name1", entidade.getName());
        Assertions.assertTrue(entidade.getId() != null);
        Assertions.assertEquals("description1", entidade.getDescription());
    }
}
