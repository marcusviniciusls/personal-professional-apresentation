package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsProfessionalUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ProfessionalExperienceFactoryTest {

    @Autowired private ProfessionalExperienceFactory professionalExperienceFactory;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private AssignmentsRepository assignmentsRepository;

    @Test
    @Transactional
    @DisplayName("Converter SaveForm em Entidade (Professional Experience)")
    public void convertSaveFormInEntityTest(){
        ProfessionalExperienceFormSave professionalExperienceFormSave = new ProfessionalExperienceFormSave();
        professionalExperienceFormSave.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperienceFormSave.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperienceFormSave.setDescription("description");
        professionalExperienceFormSave.setOfficeEnum(1);

        ProfessionalExperience entity = professionalExperienceFactory.convertSaveFormInEntity(professionalExperienceFormSave);

        Assertions.assertTrue(entity != null);
        Assertions.assertEquals(LocalDate.of(2020, 1,30), entity.getDateInit());
        Assertions.assertEquals(LocalDate.of(2022, 1,30), entity.getDateFinish());
        Assertions.assertEquals("description", entity.getDescription());
        Assertions.assertEquals(OfficeEnum.CLT, entity.getOfficeEnum());
    }

    //@Test
    //@Transactional
    //@DisplayName("Converter Entidade em Response (Professional Experience)")
    public void convertEntityInResponse(){
        Assignments assignments1 = new Assignments(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"), "desenvolver API");
        Assignments assignments2 = new Assignments(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"),"documentar API");
        Assignments assignments3 = new Assignments(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373"), "criar testes unitários");
        List<Assignments> listAssignments = new ArrayList<>();
        listAssignments.add(assignments1);
        listAssignments.add(assignments2);
        listAssignments.add(assignments3);
        ProfessionalExperience professionalExperience = new ProfessionalExperience();
        professionalExperience.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperience.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperience.setDescription("description");
        professionalExperience.setStatusWork(StatusWork.CURRENT);
        professionalExperience.setOfficeEnum(OfficeEnum.CLT);
        professionalExperience.setListAssignments(listAssignments);
        professionalExperience.setListAssignments(listAssignments);
        professionalExperience.setPartner(new Partner("IBM", "urlImage","description"));
        professionalExperience.setOffice(new Office("Desenvolvedor Júnior", "", OfficeLevel.JUNIOR));

        ProfessionalExperienceResponse response = professionalExperienceFactory.convertEntityInResponse(professionalExperience);

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
    @DisplayName("Converter UpdateForm em Entidade (Professional Experience)")
    public void convertUpdateFormInEntity(){
        // Office
        Office office = new Office("Desenvolvedor Júnior", "", OfficeLevel.JUNIOR);
        office.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        officeRepository.save(office);

        // Partner
        Partner partner = new Partner("IBM", "urlImage","description");
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
        //Entidade
        ProfessionalExperience professionalExperience = new ProfessionalExperience();
        professionalExperience.setOffice(office);
        professionalExperience.setPartner(partner);
        professionalExperience.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperience.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperience.setDescription("description");
        professionalExperience.setStatusWork(StatusWork.CURRENT);
        professionalExperience.setOfficeEnum(OfficeEnum.CLT);
        professionalExperience.setListAssignments(listAssignments);

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

        ProfessionalExperience entity = professionalExperienceFactory.convertUpdateFormInEntity(professionalExperience, professionalExperienceFormUpdate);

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
