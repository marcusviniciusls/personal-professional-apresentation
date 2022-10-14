package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import br.com.marcus.dev.personal.professional.apresentation.repository.*;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateProfessionalExperienceServiceTest {

    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private UpdateProfessionalExperienceService updateProfessionalExperienceService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @MockBean private CenterEntityService centerEntityService;

    @BeforeEach
    public void setupInit(){
        // Office
        Office office = new Office("Desenvolvedor Júnior", "", OfficeLevel.JUNIOR);
        office.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        officeRepository.save(office);

        // Branch Activity
        BranchActivity branchActivity = new BranchActivity("Comercio e Tecnologia e informacao");
        branchActivity.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        branchActivityRepository.save(branchActivity);

        // Partner
        Partner partner = new Partner("IBM", "urlImage", branchActivity, "description");
        partner.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        partnerRepository.save(partner);

        // Assignments
        Assignments assignments1 = new Assignments(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"), "desenvolver API");
        Assignments assignments2 = new Assignments(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"),"documentar API");
        Assignments assignments3 = new Assignments(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373"), "criar testes unitários");
        assignmentsRepository.saveAll(Arrays.asList(assignments1, assignments2, assignments3));
        List<Assignments> listAssignments = new ArrayList<>();
        listAssignments.add(assignments1);
        listAssignments.add(assignments2);
        listAssignments.add(assignments3);

        // Professional Experience
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalExperience professionalExperience = new ProfessionalExperience(id);
        professionalExperience.setOffice(office);
        professionalExperience.setPartner(partner);
        professionalExperience.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperience.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperience.setDescription("description");
        professionalExperience.setStatusWork(StatusWork.CURRENT);
        professionalExperience.setOfficeEnum(OfficeEnum.CLT);
        professionalExperience.setListAssignments(listAssignments);
        professionalExperienceRepository.save(professionalExperience);

        // Dados Mockados
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(professionalExperience);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Professional Experience")
    public void updateTest(){
        ProfessionalExperienceFormUpdate professionalExperienceFormUpdate = new ProfessionalExperienceFormUpdate();
        professionalExperienceFormUpdate.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperienceFormUpdate.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperienceFormUpdate.setDescription("description");
        professionalExperienceFormUpdate.setOfficeEnum(1);
        professionalExperienceFormUpdate.setPartnerId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        professionalExperienceFormUpdate.setOfficeId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        List<AssignmentsProfessionalUpdateForm> assignmentsSaveFormList = new ArrayList<>();
        assignmentsSaveFormList.add(new AssignmentsProfessionalUpdateForm("desenvolver API"));
        assignmentsSaveFormList.add(new AssignmentsProfessionalUpdateForm("documentar API"));
        assignmentsSaveFormList.add(new AssignmentsProfessionalUpdateForm("criar testes unitarios API"));
        professionalExperienceFormUpdate.setListAssignments(assignmentsSaveFormList);

        updateProfessionalExperienceService.update(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"), professionalExperienceFormUpdate);

        ProfessionalExperience entity = professionalExperienceRepository.findById(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371")).get();

        Assertions.assertTrue(entity != null);
        Assertions.assertTrue(entity.getPartner() != null);
        Assertions.assertTrue(entity.getOffice() != null);
        Assertions.assertEquals(LocalDate.of(2020, 1,30), entity.getDateInit());
        Assertions.assertEquals(LocalDate.of(2022, 1,30), entity.getDateFinish());
        Assertions.assertEquals("description", entity.getDescription());
        Assertions.assertEquals(OfficeEnum.CLT, entity.getOfficeEnum());
        Assertions.assertEquals(StatusWork.CURRENT, entity.getStatusWork());
        Assertions.assertEquals("IBM", entity.getPartner().getName());
        Assertions.assertEquals("urlImage", entity.getPartner().getUrlImage());
        Assertions.assertEquals("description", entity.getPartner().getDescription());
        Assertions.assertEquals("Desenvolvedor Júnior", entity.getOffice().getName());
        Assertions.assertEquals(OfficeLevel.JUNIOR, entity.getOffice().getOfficeLevel());
        Assertions.assertEquals("", entity.getOffice().getDescription());
        Assertions.assertEquals(3, entity.getListAssignments().size());
    }
}
