package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BranchActivityFactoryTest {

    @Autowired private BranchActivityFactory branchActivityFactory;

    @Test
    @DisplayName("Converter Entidade em Resposta (Branch Activity)")
    public void convertEntityInResponseTest(){
        BranchActivity branchActivity = new BranchActivity("Branch Activity");
        BranchActivityResponse response = branchActivityFactory.convertEntityInResponse(branchActivity);
        Assertions.assertEquals("Branch Activity", response.getName());
        Assertions.assertTrue(response != null);
    }

    @Test
    @DisplayName("Converter Resquest em Entidade (Branch Activity)")
    public void convertRequestInEntityTest(){
        BranchActivityForm branchActivityForm = new BranchActivityForm("Branch Activity");
        BranchActivity branchActivity = branchActivityFactory.convertRequestInEntity(branchActivityForm);
        Assertions.assertEquals("Branch Activity", branchActivity.getName());
        Assertions.assertTrue(branchActivity != null);
    }

    @Test
    @DisplayName("Converter Entidade em Update (Branch Activity)")
    public void convertEntityToUpdateTest(){
        BranchActivityForm branchActivityForm = new BranchActivityForm("Nova");
        BranchActivity branchActivity = new BranchActivity("Branch Activity");
        branchActivity = branchActivityFactory.convertEntityToUpdate(branchActivityForm, branchActivity);
        Assertions.assertEquals("Nova", branchActivity.getName());
        Assertions.assertTrue(branchActivity != null);
    }
}
