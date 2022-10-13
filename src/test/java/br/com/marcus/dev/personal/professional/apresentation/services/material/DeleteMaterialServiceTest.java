package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
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
public class DeleteMaterialServiceTest {

    @Autowired private DeleteMaterialService deleteMaterialService;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private PartRepository partRepository;

    @BeforeEach
    public void setupInit(){
        // Preparacao
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Part part = new Part("Part", Level.ADVANCED);
        partRepository.save(part);
        Material material = new Material(id, "material", part);
        materialRepository.save(material);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Material")
    public void deleteTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        deleteMaterialService.delete(id);
        // Testes Unitários
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        Assertions.assertTrue(optionalMaterial.isEmpty());
    }
}
