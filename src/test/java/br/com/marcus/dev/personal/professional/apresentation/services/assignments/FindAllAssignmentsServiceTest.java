package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllAssignmentsServiceTest {

    @Autowired private PartnerRepository partnerRepository;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private FindAllAssignmentsService findAllAssignmentsService;

    @BeforeEach
    public void setupInit(){
        ProfessionalExperience professionalExperience = new ProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"));
        professionalExperienceRepository.save(professionalExperience);

        // Assignments
        Assignments assignments1 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"), "Description", professionalExperience);
        Assignments assignments2 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"), "Description", professionalExperience);
        Assignments assignments3 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff703"), "Description", professionalExperience);
        Assignments assignments4 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff704"), "Description", professionalExperience);
        Assignments assignments5 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff705"), "Description", professionalExperience);
        Assignments assignments6 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff706"), "Description", professionalExperience);
        Assignments assignments7 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff707"), "Description", professionalExperience);
        Assignments assignments8 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff708"), "Description", professionalExperience);
        Assignments assignments9 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff709"), "Description");
        Assignments assignments10 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff710"), "Description");
        Assignments assignments11 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff711"), "Description");
        assignments8.setStatus(false);
        assignments7.setStatus(false);

        // Salvar Assignments
        assignmentsRepository.saveAll(Arrays.asList(assignments1, assignments2, assignments3, assignments4, assignments5
        , assignments6, assignments7, assignments8, assignments9, assignments10, assignments11));
    }

    @Test
    //@DisplayName("Deletar um Assignments com sucesso")
    public void findAllTest(){
        UUID id = UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712");
        Page<AssignmentsResponse> response = findAllAssignmentsService.findAll(PageRequest.of(1, 6), id);
        //Assertions.assertEquals(6, response.getSize());
    }
}
