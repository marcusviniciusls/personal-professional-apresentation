package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.StudyPlanResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.*;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdStudyPlanServiceTest {

    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private FindByIdStudyPlanService findByIdStudyPlanService;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private TopicRepository topicRepository;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    @BeforeEach
    public void setupInit(){
        // BranchActivity
        BranchActivity branchActivity = new BranchActivity("Ramo de Atividade");
        branchActivityRepository.save(branchActivity);
        // Partner
        Partner partner = new Partner("AXELOS", "urlImage", branchActivity, "description");
        partnerRepository.save(partner);
        // Certificate
        Certificate certificate1 = new Certificate("ITIL V3", "logoImage", partner);
        Certificate certificate2 = new Certificate("ITIL V4", "logoImage", partner);
        certificateRepository.save(certificate1);
        certificateRepository.save(certificate2);
        // Graduation
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduationRepository.save(graduation);
        // Office
        Office office = new Office("Desenvolvedor Java", "description", OfficeLevel.SENIOR);
        officeRepository.save(office);
        // Professional Experience
        ProfessionalExperience professionalExperience = new ProfessionalExperience();
        professionalExperience.setOffice(office);
        professionalExperience.setPartner(partner);
        professionalExperience.setDateInit(LocalDate.of(2020, 1,30));
        professionalExperience.setDateFinish(LocalDate.of(2022, 1,30));
        professionalExperience.setDescription("description");
        professionalExperience.setStatusWork(StatusWork.CURRENT);
        professionalExperience.setOfficeEnum(OfficeEnum.CLT);
        professionalExperienceRepository.save(professionalExperience);
        // Assignments
        Assignments assignments = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"), "Description", professionalExperience);
        Assignments assignments2 = new Assignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"), "Description", professionalExperience);
        assignmentsRepository.save(assignments);
        assignmentsRepository.save(assignments2);
        // Course
        Course course = new Course("Name1", "description1", BigDecimal.ZERO
                , LocalDate.of(2022, 10,8), LocalDate.of(2022, 10,8)
                , LocalDate.of(2022, 10,8), LocalDate.of(2022, 10,8)
                , "logoImagem", StatusCourse.CONCLUSION, LevelCourse.BASIC);
        courseRepository.save(course);
        // Topic
        Topic topic1 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Topic topic2 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        topicRepository.save(topic1);
        topicRepository.save(topic2);
        // Framework
        Framework framework = new Framework("Spring", "description", "urlImage");
        frameworkRepository.save(framework);
        // LanguageProgrammer
        LanguageProgrammer languageProgrammer = new LanguageProgrammer("Java", "");
        languageProgrammerRepository.save(languageProgrammer);

        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        StudyPlan studyPlan = new StudyPlan(id);
        studyPlan.setName("studyPlan");
        studyPlan.setLevel(Level.BASIC);

        // Vinculando
        studyPlan.setLanguageProgrammer(languageProgrammer);
        studyPlan.setFramework(framework);
        studyPlan.addCourse(course);
        studyPlan.addProfessionalExperience(professionalExperience);
        studyPlan.addAssignments(assignments);
        studyPlan.addAssignments(assignments2);
        studyPlan.addGraduation(graduation);
        studyPlan.addCertificate(certificate1);
        studyPlan.addCertificate(certificate2);
        studyPlanRepository.save(studyPlan);

        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        StudyPlan studyPlanStatusFalse = new StudyPlan(idStatusFalse);
        studyPlanStatusFalse.setName("studyPlan");
        studyPlanStatusFalse.setLevel(Level.BASIC);
        studyPlanStatusFalse.setStatus(false);
        studyPlanRepository.save(studyPlanStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar StudyPlan por Id")
    public void findByIdTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        StudyPlan entity = findByIdStudyPlanService.findByIdEntity(id);
        // Testes Unitários
        Assertions.assertTrue(entity != null);
        Assertions.assertEquals("studyPlan", entity.getName());
        Assertions.assertEquals(Level.BASIC, entity.getLevel());
        Assertions.assertEquals("Java", entity.getLanguageProgrammer().getName());
        Assertions.assertEquals("", entity.getLanguageProgrammer().getDescription());
        Assertions.assertEquals("Spring", entity.getFramework().getName());
        Assertions.assertEquals("description", entity.getFramework().getDescription());
        Assertions.assertEquals(2, entity.getListCertificate().size());
        Assertions.assertEquals(1, entity.getListGraduation().size());
        Assertions.assertEquals(2, entity.getListAssignments().size());
        Assertions.assertEquals(1, entity.getListProfessionalExperience().size());
        Assertions.assertEquals(1, entity.getListCourse().size());
    }

    @Test
    @Transactional
    @DisplayName("Buscar StudyPlan por Id - Status False")
    public void findByIdStatusFalseTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdStudyPlanService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar StudyPlan por Id - Not Found")
    public void findByIdNotFoundTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff377");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdStudyPlanService.findByIdEntity(id));
    }
}
