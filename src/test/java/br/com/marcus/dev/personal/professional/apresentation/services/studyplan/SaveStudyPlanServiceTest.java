package br.com.marcus.dev.personal.professional.apresentation.services.studyplan;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.*;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.StudyPlanResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.*;
import br.com.marcus.dev.personal.professional.apresentation.repository.*;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveStudyPlanServiceTest {

    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private SaveStudyPlanService saveStudyPlanService;
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
    @MockBean private CenterEntityService centerEntityService;

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
        certificate1.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        certificate2.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        certificateRepository.save(certificate1);
        certificateRepository.save(certificate2);
        // Graduation
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
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
        professionalExperience.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
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
        course.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        courseRepository.save(course);
        // Topic
        Topic topic1 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"), "teste1");
        Topic topic2 = new Topic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"), "teste2");
        topicRepository.save(topic1);
        topicRepository.save(topic2);
        // Framework
        Framework framework = new Framework("Spring", "description", "urlImage");
        framework.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        frameworkRepository.save(framework);
        // LanguageProgrammer
        LanguageProgrammer languageProgrammer = new LanguageProgrammer("Java", "");
        languageProgrammer.setId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
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
        studyPlan.addTopic(topic1);
        studyPlan.addTopic(topic2);
        studyPlanRepository.save(studyPlan);

        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(studyPlan);
    }

    @Test
    @Transactional
    @DisplayName("Salvar StudyPlan")
    public void saveTest(){
        StudyPlanSaveForm studyPlanSaveForm = new StudyPlanSaveForm("studyPlan", 1);
        studyPlanSaveForm.setFrameworkId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        studyPlanSaveForm.setLanguageProgrammerId(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        // Topic
        List<ListTopic> listTopic = new ArrayList<>();
        listTopic.add(new ListTopic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701")));
        listTopic.add(new ListTopic(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702")));
        studyPlanSaveForm.setListTopic(listTopic);
        // Course
        List<ListCourse> listCourse = new ArrayList<>();
        listCourse.add(new ListCourse(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701")));
        studyPlanSaveForm.setListCourse(listCourse);
        // Professional Experience
        List<ListProfessionalExperience> listProfessionalExperience = new ArrayList<>();
        listProfessionalExperience.add(new ListProfessionalExperience(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701")));
        studyPlanSaveForm.setListProfessionalExperience(listProfessionalExperience);
        // Assignments
        List<ListAssignments> listAssignments = new ArrayList<>();
        listAssignments.add(new ListAssignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701")));
        listAssignments.add(new ListAssignments(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702")));
        studyPlanSaveForm.setListAssignments(listAssignments);
        // Graduation
        List<ListGraduation> listGraduation = new ArrayList<>();
        listGraduation.add(new ListGraduation(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701")));
        studyPlanSaveForm.setListGraduation(listGraduation);
        // Certificate
        List<ListCertificate> listCertificate = new ArrayList<>();
        listCertificate.add(new ListCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701")));
        listCertificate.add(new ListCertificate(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702")));
        studyPlanSaveForm.setListCertificate(listCertificate);

        StudyPlanResponse response = saveStudyPlanService.save(studyPlanSaveForm);

        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("studyPlan", response.getName());
        Assertions.assertEquals(1, response.getLevel());
        Assertions.assertEquals("Java", response.getLanguageProgrammerResponse().getName());
        Assertions.assertEquals("", response.getLanguageProgrammerResponse().getDescription());
        Assertions.assertEquals("Spring", response.getFrameworkResponse().getName());
        Assertions.assertEquals("description", response.getFrameworkResponse().getDescription());
        Assertions.assertEquals(2, response.getListCertificateResponse().size());
        Assertions.assertEquals(1, response.getListGraduationResponse().size());
        Assertions.assertEquals(2, response.getListAssignmentsResponse().size());
        Assertions.assertEquals(1, response.getListProfessionalExperienceResponse().size());
        Assertions.assertEquals(1, response.getListCourseResponse().size());
    }
}
