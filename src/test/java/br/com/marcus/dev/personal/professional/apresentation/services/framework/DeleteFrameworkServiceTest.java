package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteFrameworkServiceTest {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private DeleteFrameworkService deleteFrameworkService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework = new Framework(id, "Quarkus", "description", "url");
        frameworkRepository.save(framework);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Framework com sucesso")
    public void deleteTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        deleteFrameworkService.delete(id);
        // Buscar o Framework
        Optional<Framework> optionalFramework = frameworkRepository.findById(id);
        // Teste
        Assertions.assertTrue(optionalFramework.isEmpty());
    }
}
