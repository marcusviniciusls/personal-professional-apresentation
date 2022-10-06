package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveAddForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
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
public class SaveAssignmentsServiceTest {

    @Autowired private SaveAssignmentsService saveAssignmentsService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;

    @BeforeEach
    public void setupInit(){
        ProfessionalExperience professionalExperience = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));
        professionalExperienceRepository.save(professionalExperience);
    }

    @Test
    @DisplayName("Salvar Assignments com sucesso (save)")
    public void saveTest(){
        Assignments assignments = new Assignments();
        assignments.setDescription("description");
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(assignments);
        assignments = saveAssignmentsService.save(assignments);
        UUID id = assignments.getId();
        Optional<Assignments> optionalAssignments = assignmentsRepository.findById(id);
        Assertions.assertTrue(optionalAssignments.isPresent());
    }

    @Test
    @DisplayName("Salvar Assignments com sucesso (addSave)")
    public void addSaveTest(){
        AssignmentsSaveAddForm assignmentsSaveAddForm = new AssignmentsSaveAddForm();
        assignmentsSaveAddForm.setDescription("description");
        assignmentsSaveAddForm.setIdProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));
        AssignmentsResponse response = saveAssignmentsService.addSave(assignmentsSaveAddForm);
        Assertions.assertEquals("description", response.getDescription());
    }
}
