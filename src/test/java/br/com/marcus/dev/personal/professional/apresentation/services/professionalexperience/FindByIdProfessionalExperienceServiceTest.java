package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class FindByIdProfessionalExperienceServiceTest {

    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private FindByIdProfessionalExperienceService findByIdProfessionalExperienceService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;

    @BeforeEach
    public void setupInit(){
        // Office
        Office office = new Office("Desenvolvedor Júnior", "", OfficeLevel.JUNIOR);
        officeRepository.save(office);

        // Branch Activity
        BranchActivity branchActivity = new BranchActivity("Comercio e Tecnologia");
        branchActivityRepository.save(branchActivity);

        // Partner
        Partner partner = new Partner("IBM", "urlImage", branchActivity, "description");
        partnerRepository.save(partner);

        // Assignments
        Assignments assignments1 = new Assignments("desenvolver API");
        Assignments assignments2 = new Assignments("documentar API");
        Assignments assignments3 = new Assignments("criar testes unitários");
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

        // Professional Experience
        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        ProfessionalExperience professionalExperienceStatusFalse = new ProfessionalExperience(idStatusFalse);
        professionalExperienceStatusFalse.setOffice(office);
        professionalExperienceStatusFalse.setPartner(partner);
        professionalExperienceStatusFalse.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperienceStatusFalse.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperienceStatusFalse.setDescription("description");
        professionalExperienceStatusFalse.setStatusWork(StatusWork.CURRENT);
        professionalExperienceStatusFalse.setOfficeEnum(OfficeEnum.CLT);
        professionalExperienceStatusFalse.setListAssignments(listAssignments);
        professionalExperienceStatusFalse.setStatus(false);
        professionalExperienceRepository.save(professionalExperienceStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Professional Experience por Id")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalExperienceResponse response = findByIdProfessionalExperienceService.findById(id);

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
    @DisplayName("Buscar Professional Experience por Id")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalExperience entity = findByIdProfessionalExperienceService.findByIdEntity(id);

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

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id Status False")
    public void findByIdStatusFalseTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalExperienceService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id Status False")
    public void findByIdEntityStatusFalseTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalExperienceService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id notFound")
    public void findByIdNotFoundTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalExperienceService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Partner por Id notFound")
    public void findByIdEntityNotFoundTest(){
        // Execucao do teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalExperienceService.findByIdEntity(id));
    }
}
