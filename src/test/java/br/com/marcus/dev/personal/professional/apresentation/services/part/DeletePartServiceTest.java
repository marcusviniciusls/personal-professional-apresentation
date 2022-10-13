package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
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
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeletePartServiceTest {

    @Autowired private PartRepository partRepository;
    @Autowired private DeletePartService deletePartService;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Part part = new Part(id, "part", Level.ADVANCED);
        partRepository.save(part);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Part")
    public void deleteTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deletePartService.delete(id);
        Optional<Part> optionalPart = partRepository.findById(id);
        // Teste Unitário
        Assertions.assertTrue(optionalPart.isEmpty());
    }
}
