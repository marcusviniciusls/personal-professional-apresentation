package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdBranchActivityTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private FindByIdBranchActivity findByIdBranchActivity;

    @BeforeEach
    public void setupInit(){
        BranchActivity branchActivity1 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff200"), "Atividade1");
        BranchActivity branchActivity2 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff201"), "Atividade2");
        branchActivity2.setStatus(false);
        branchActivityRepository.save(branchActivity1);
        branchActivityRepository.save(branchActivity2);
    }

    @Test
    @DisplayName("Buscar todos as Branch Activity por Id com sucesso")
    public void findByIdTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff200");
        BranchActivityResponse branchActivityResponse = findByIdBranchActivity.findById(id);
        Assertions.assertTrue(branchActivityResponse != null);
        Assertions.assertEquals("Atividade1", branchActivityResponse.getName());
    }

    @Test
    @DisplayName("Buscar todos as Branch Activity por Id e verificar se está com o status false")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff201");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdBranchActivity.findById(id));
    }

    @Test
    @DisplayName("Buscar todos as Branch Activity por Id e nao achar")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff202");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdBranchActivity.findById(id));
    }

    @Test
    @DisplayName("Buscar todos as Branch Activity por Id com sucesso")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff200");
        BranchActivity branchActivity = findByIdBranchActivity.findByIdEntity(id);
        Assertions.assertTrue(branchActivity != null);
        Assertions.assertEquals("Atividade1", branchActivity.getName());
    }

    @Test
    @DisplayName("Buscar todos as Branch Activity por Id e verificar se está com o status false")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff201");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdBranchActivity.findByIdEntity(id));
    }

    @Test
    @DisplayName("Buscar todos as Branch Activity por Id e nao achar")
    public void findByIdEntityNotFoundTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff202");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdBranchActivity.findByIdEntity(id));
    }
}
