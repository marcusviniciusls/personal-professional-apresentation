package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteBranchActivityServiceTest {

    @Autowired private DeleteBranchActivityService deleteBranchActivityService;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @DisplayName("Deletar um Branch Activity com sucesso")
    public void deleteBranchActivitySuccesoTest(){
        BranchActivity branchActivity1 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff715"), "teste12345");
        branchActivity1.setStatus(true);
        branchActivityRepository.save(branchActivity1);
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff715");
        deleteBranchActivityService.deleteBranchActivity(id);
        Optional<BranchActivity> optionalBranchActivity = branchActivityRepository.findById(id);
        Assertions.assertTrue(optionalBranchActivity.isEmpty());
    }
}
