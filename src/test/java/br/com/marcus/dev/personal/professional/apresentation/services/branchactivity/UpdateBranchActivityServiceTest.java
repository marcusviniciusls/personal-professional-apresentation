package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
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

import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateBranchActivityServiceTest {

    @Autowired private UpdateBranchActivityService updateBranchActivityService;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        BranchActivity branchActivity1 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff300"), "Industria01");
        BranchActivity branchActivity2 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff301"), "Industria02");
        branchActivity2.setStatus(false);
        branchActivityRepository.saveAll(Arrays.asList(branchActivity1, branchActivity2));
    }

    @Test
    @DisplayName("Atualizar um BranchActivity com sucesso")
    public void updateTest(){
        // Mockando Dados
        BranchActivity branchActivity1 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff300"), "Industria01");
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(branchActivity1);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        // Formulário para atualizar
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff300");
        BranchActivityForm branchActivityForm = new BranchActivityForm("Teste - Teste");
        BranchActivityResponse response = updateBranchActivityService.update(branchActivityForm, id);
        // Teste
        Assertions.assertEquals("Industria01", response.getName());
    }

    @Test
    @DisplayName("Atualizar um BranchActivity e nao achar")
    public void updateStatusFalseTest(){
        // Formulário para atualizar
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff301");
        BranchActivityForm branchActivityForm = new BranchActivityForm("Teste - Teste");
        // Teste
        Assertions.assertThrows(ResourceNotFoundException.class, () -> updateBranchActivityService.update(branchActivityForm, id));
    }

    @Test
    @DisplayName("Atualizar um BranchActivity e nao achar")
    public void updateNotFoundTest(){
        // Formulário para atualizar
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff302");
        BranchActivityForm branchActivityForm = new BranchActivityForm("Teste - Teste");
        // Teste
        Assertions.assertThrows(ResourceNotFoundException.class, () -> updateBranchActivityService.update(branchActivityForm, id));
    }
}
