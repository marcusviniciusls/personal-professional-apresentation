package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
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
public class FindByIdAssignmentsServiceTest {

    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private FindByIdAssignmentsService findByIdAssignmentsService;

    @BeforeEach
    public void setupInit(){
        ProfessionalExperience professionalExperience = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));
        professionalExperienceRepository.save(professionalExperience);

        // Assignments
        Assignments assignments = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"), "Description", professionalExperience);
        Assignments assignments2 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"), "Description", professionalExperience);
        assignments2.setStatus(false);
        assignmentsRepository.save(assignments);
        assignmentsRepository.save(assignments2);
    }

    @Test
    @DisplayName("Buscar Assignments por ID com sucesso")
    public void findByIdEntitySuccessTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701");
        Assignments assignments = findByIdAssignmentsService.findByIdEntity(id);
        Assertions.assertTrue(assignments != null);
    }

    @Test
    @DisplayName("Buscar Assignments e nao encontrar")
    public void findByIdEntityResourceNotFoundTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdAssignmentsService.findByIdEntity(id));
    }

    @Test
    @DisplayName("Buscar Assignments e nao encontrar")
    public void findByIdEntityResourceNotFoundStatusTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdAssignmentsService.findByIdEntity(id));
    }
}
