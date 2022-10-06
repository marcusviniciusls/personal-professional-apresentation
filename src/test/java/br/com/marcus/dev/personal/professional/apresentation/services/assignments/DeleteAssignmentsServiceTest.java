package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import br.com.marcus.dev.personal.professional.apresentation.repository.*;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteAssignmentsServiceTest {

    @Autowired private PartnerRepository partnerRepository;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private DeleteAssignmentsService deleteAssignmentsService;

    @BeforeEach
    public void setupInit(){
        BranchActivity branchActivity = new BranchActivity("teste");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("name", "urlImage", branchActivity, "description");
        partnerRepository.save(partner);
        Office office = new Office("name", "description", OfficeLevel.INTERNSHIP);
        officeRepository.save(office);
        ProfessionalExperience professionalExperience = new ProfessionalExperience(LocalDate.now(), LocalDate.now()
                , OfficeEnum.CLT, StatusWork.OLD, partner, office, "description");
        professionalExperienceRepository.save(professionalExperience);
        Assignments assignments = new Assignments("Description", professionalExperience);
        assignments.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        assignmentsRepository.save(assignments);
    }

    @Test
    @DisplayName("Deletar um Assignments com sucesso")
    public void deleteTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701");
        deleteAssignmentsService.delete(id);
        Optional<Assignments> optionalAssignments = assignmentsRepository.findById(id);
        Assertions.assertTrue(optionalAssignments.isEmpty());
    }
}
