package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorDateAfterNowProfessionalExperience;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveProfessionalServiceTest {

    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private SaveProfessionalExperienceService saveProfessionalExperienceService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SaveActivitiesService saveActivitiesService;

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
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(professionalExperience);
        BDDMockito.given(saveActivitiesService.saveMovementProfessionalExperience(Mockito.any(ProfessionalExperience.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Salvar Professional Experience")
    public void saveTest(){
        ProfessionalExperienceFormSave professionalExperienceFormSave = new ProfessionalExperienceFormSave();
        professionalExperienceFormSave.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperienceFormSave.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperienceFormSave.setDescription("description");
        professionalExperienceFormSave.setOfficeEnum(1);
        professionalExperienceFormSave.setPartnerId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        professionalExperienceFormSave.setOfficeId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        List<AssignmentsSaveForm> assignmentsSaveFormList = new ArrayList<>();
        assignmentsSaveFormList.add(new AssignmentsSaveForm("desenvolver API"));
        assignmentsSaveFormList.add(new AssignmentsSaveForm("documentar API"));
        assignmentsSaveFormList.add(new AssignmentsSaveForm("criar testes unitarios API"));

        ProfessionalExperienceResponse response = saveProfessionalExperienceService.save(professionalExperienceFormSave);

        Assertions.assertTrue(response != null);
        Assertions.assertTrue(response.getPartnerResponse() != null);
        Assertions.assertTrue(response.getOfficeResponse() != null);
        Assertions.assertEquals(LocalDate.of(2020, 1,30), response.getDateInit());
        Assertions.assertEquals(LocalDate.of(2022, 1,30), response.getDateFinish());
        Assertions.assertEquals("description", response.getDescription());
        Assertions.assertEquals(OfficeEnum.CLT, response.getOfficeEnum());
        Assertions.assertEquals(StatusWork.CURRENT, response.getStatusWork());
        Assertions.assertEquals("IBM", response.getPartnerResponse().getName());
        Assertions.assertEquals("urlImage", response.getPartnerResponse().getUrlImage());
        Assertions.assertEquals("description", response.getPartnerResponse().getDescription());
        Assertions.assertEquals("Desenvolvedor Júnior", response.getOfficeResponse().getName());
        Assertions.assertEquals(OfficeLevel.JUNIOR, response.getOfficeResponse().getOfficeLevel());
        Assertions.assertEquals("", response.getOfficeResponse().getDescription());
        Assertions.assertEquals(3, response.getListAssignmentsResponse().size());
    }

    @Test
    @Transactional
    @DisplayName("Montar Professional Experience")
    public void mountObjectTest(){
        ProfessionalExperienceFormSave professionalExperienceFormSave = new ProfessionalExperienceFormSave();
        professionalExperienceFormSave.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperienceFormSave.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperienceFormSave.setDescription("description");
        professionalExperienceFormSave.setOfficeEnum(1);
        professionalExperienceFormSave.setPartnerId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        professionalExperienceFormSave.setOfficeId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        List<AssignmentsSaveForm> assignmentsSaveFormList = new ArrayList<>();
        assignmentsSaveFormList.add(new AssignmentsSaveForm("desenvolver API"));
        assignmentsSaveFormList.add(new AssignmentsSaveForm("documentar API"));
        assignmentsSaveFormList.add(new AssignmentsSaveForm("criar testes unitarios API"));

        ProfessionalExperience entity = saveProfessionalExperienceService.mountObject(professionalExperienceFormSave);

        Assertions.assertTrue(entity != null);
        Assertions.assertTrue(entity.getPartner() != null);
        Assertions.assertTrue(entity.getOffice() != null);
        Assertions.assertEquals(LocalDate.of(2020, 1,30), entity.getDateInit());
        Assertions.assertEquals(LocalDate.of(2022, 1,30), entity.getDateFinish());
        Assertions.assertEquals("description", entity.getDescription());
        Assertions.assertEquals(OfficeEnum.CLT, entity.getOfficeEnum());
        Assertions.assertEquals("IBM", entity.getPartner().getName());
        Assertions.assertEquals("urlImage", entity.getPartner().getUrlImage());
        Assertions.assertEquals("description", entity.getPartner().getDescription());
        Assertions.assertEquals("Desenvolvedor Júnior", entity.getOffice().getName());
        Assertions.assertEquals(OfficeLevel.JUNIOR, entity.getOffice().getOfficeLevel());
        Assertions.assertEquals("", entity.getOffice().getDescription());
    }

    @Test
    @Transactional
    @DisplayName("Verificar se já tem algum trabalho vigente")
    public void verifyStatusJobTest(){
        boolean verify = saveProfessionalExperienceService.verifyStatusJob();
        Assertions.assertTrue(verify);
    }

    @Test
    @Transactional
    @DisplayName("Verificar se a data passada é maior que hoje")
    public void dateFinishBeforeNotAllowedTest(){
        LocalDate date = LocalDate.of(2050, 1, 1);
        Assertions.assertThrows(ErrorDateAfterNowProfessionalExperience.class, () -> saveProfessionalExperienceService.dateFinishBeforeNotAllowed(date));
    }
}
