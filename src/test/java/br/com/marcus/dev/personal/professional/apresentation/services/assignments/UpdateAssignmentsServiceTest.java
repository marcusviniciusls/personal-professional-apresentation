package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
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
public class UpdateAssignmentsServiceTest {

    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private UpdateAssignmentsService updateAssignmentsService;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;

    @Test
    @DisplayName("Atualizar um Assignments com sucesso")
    public void updateTest(){
        // Professional Experience
        ProfessionalExperience professionalExperience = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));
        ProfessionalExperience professionalExperienceRefresh = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff713"));
        professionalExperienceRefresh.setStatus(true);
        professionalExperience.setStatus(true);
        professionalExperienceRepository.save(professionalExperience);
        professionalExperienceRepository.save(professionalExperienceRefresh);

        // Assignments
        Assignments assignments = new Assignments();
        assignments.setDescription("Description");
        assignments.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        assignments.setProfessionalExperience(professionalExperience);
        assignmentsRepository.save(assignments);

        // Dados de Entrada para fazer o teste
        AssignmentsUpdateForm assignmentsUpdateForm = new AssignmentsUpdateForm();
        assignmentsUpdateForm.setDescription("Atualizado");
        assignmentsUpdateForm.setIdProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff713"));

        // Executar a operacao
        UUID idAssignmentsToUpdate = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701");
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(assignments);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        updateAssignmentsService.update(assignmentsUpdateForm, idAssignmentsToUpdate);

        // Teste
        Optional<Assignments> optionalAssignments = assignmentsRepository.findById(idAssignmentsToUpdate);
        Assertions.assertTrue(optionalAssignments.isPresent());
    }
}
