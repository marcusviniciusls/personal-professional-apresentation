package br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveAddForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssignmentsFactoryTest {

    @Autowired private AssignmentsFactory assignmentsFactory;

    @Test
    @DisplayName("Converter SaveForm em Entidade")
    public void convertSaveFormInEntityTest(){
        AssignmentsSaveForm assignmentsSaveForm = new AssignmentsSaveForm();
        assignmentsSaveForm.setDescription("Teste");
        Assignments assignments = assignmentsFactory.convertSaveFormInEntity(assignmentsSaveForm);
        Assertions.assertEquals("Teste", assignments.getDescription());
        Assertions.assertTrue(assignments != null);
    }

    @Test
    @DisplayName("Converter Entidade em Resposta")
    public void convertEntityInResponseTest(){
        Assignments assignments = new Assignments();
        assignments.setDescription("Teste");
        AssignmentsResponse response = assignmentsFactory.convertEntityInResponse(assignments);
        Assertions.assertEquals("Teste", response.getDescription());
        Assertions.assertTrue(response != null);
    }

    @Test
    @DisplayName("Converter SaveAddForm em Entidade")
    public void convertSaveFormAddInEntityTest(){
        AssignmentsSaveAddForm form = new AssignmentsSaveAddForm();
        form.setDescription("Teste");
        Assignments assignments = assignmentsFactory.convertSaveFormAddInEntity(form);
        Assertions.assertEquals("Teste", assignments.getDescription());
        Assertions.assertTrue(assignments != null);
    }

    @Test
    @DisplayName("Converter Update Form em Entidade")
    public void convertUpdateFormInEntityTest(){
        AssignmentsUpdateForm assignmentsUpdateForm = new AssignmentsUpdateForm("Para Atualizar");
        Assignments assignments = new Assignments("Salvo no Banco");
        assignments = assignmentsFactory.convertUpdateFormInEntity(assignmentsUpdateForm, assignments);
        Assertions.assertEquals("Para Atualizar", assignments.getDescription());
    }
}
