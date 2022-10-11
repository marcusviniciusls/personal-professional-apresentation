package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdFrameworkServiceTest {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private FindByIdFrameworkService findByIdFrameworkService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework = new Framework(id, "Quarkus", "description", "url");
        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302");
        Framework framework1 = new Framework(idStatusFalse, "Quarkus", "description", "url");
        framework1.setStatus(false);
        frameworkRepository.save(framework);
        frameworkRepository.save(framework1);
    }

    @Test
    @DisplayName("Buscar um Framework por Id")
    public void findByIdTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        FrameworkResponse response = findByIdFrameworkService.findById(id);
        // Teste Unitários
        Assertions.assertEquals("Quarkus", response.getName());
        Assertions.assertEquals(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"), response.getId());
        Assertions.assertEquals("description", response.getDescription());
    }

    @Test
    @DisplayName("Buscar um Framework por Id")
    public void findByIdEntityTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework = findByIdFrameworkService.findByIdEntity(id);
        // Teste Unitários
        Assertions.assertEquals("Quarkus", framework.getName());
        Assertions.assertEquals(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"), framework.getId());
        Assertions.assertEquals("description", framework.getDescription());
    }

    @Test
    @DisplayName("Buscar um Framework por Id e erro por está marcado como false")
    public void findByIdStatusFalseTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdFrameworkService.findById(id));
    }

    @Test
    @DisplayName("Buscar um Framework por Id e erro por está marcado como false")
    public void findByIdEntityStatusFalseTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff302");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdFrameworkService.findByIdEntity(id));
    }

    @Test
    @DisplayName("Buscar um Framework por Id e erro Not found")
    public void findByIdNotFoundTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdFrameworkService.findById(id));
    }

    @Test
    @DisplayName("Buscar um Framework por Id e erro Not found")
    public void findByIdEntityNotFoundTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        // Teste Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdFrameworkService.findByIdEntity(id));
    }

    @Test
    @DisplayName("Buscar um Framework por Id e erro Not found")
    public void findByIdNotErrorTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        // Teste Unitários
        Optional<Framework> optionalFramework = findByIdFrameworkService.findByIdNotError(id);
        Assertions.assertTrue(optionalFramework.isPresent());
    }
}
