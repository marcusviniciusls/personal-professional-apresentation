package br.com.marcus.dev.personal.professional.apresentation.config.initdata;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.*;
import br.com.marcus.dev.personal.professional.apresentation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

@Configuration
@org.springframework.context.annotation.Profile("dev")
public class InitializationData implements CommandLineRunner {

    @Autowired private UserRepository userRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private EmailRepository emailRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private PartRepository partRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private ProfessionalExperienceResumeRepository professionalExperienceResumeRepository;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private StudyPlanRepository studyPlanRepository;
    @Autowired private TopicRepository topicRepository;
    @Autowired private ActivitiesRepository activitiesRepository;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // User
        User user = new User();
        User administrator = new User();
        user.setEmail("marcus@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("1234567890"));
        user.setName("Marcus");
        administrator.setEmail("vinicius@email.com");
        administrator.setPassword(bCryptPasswordEncoder.encode("1234567890"));
        administrator.setName("Vinicius");
        user.addProfile(Profile.ADMIN);
        administrator.addProfile(Profile.CLIENT);
        userRepository.saveAll(Arrays.asList(user, administrator));

        // Ramo de Atividade
        BranchActivity branchActivity = new BranchActivity("Tecnologia");
        branchActivity.setUser(user);
        branchActivity.setUserCreation(user.getName());
        BranchActivity branchActivity1 = new BranchActivity("Consultoria");
        branchActivity1.setUser(administrator);
        branchActivity1.setUserCreation(administrator.getName());
        branchActivityRepository.saveAll(Arrays.asList(branchActivity, branchActivity1));

        // Empresas
        Partner partner = new Partner("T&G Bolsas", "http://vivalinux.com/imagem.png", branchActivity,
               "TESTE DE EMPRESA PARA INCLUSAO");
        Partner partner1 = new Partner("Tech Informatica", "http://vivalinux.com/tech.png", branchActivity1,
               "TESTE DE EMPRESA PARA INCLUSAO");
        partner.setUser(user);
        partner.setUserCreation(user.getName());
        partner1.setUser(administrator);
        partner1.setUserCreation(administrator.getName());
        partnerRepository.saveAll(Arrays.asList(partner, partner1));

        // Dados Pessoais
        DataPersonal dataPersonal = new DataPersonal("Marcus Vinicius", (byte) 27, LocalDate.of(2022, 4, 20), MaritalStatus.MARRIED);
        DataPersonal dataPersonal1 = new DataPersonal("Nicolas Silva", (byte) 19, LocalDate.of(2010, 9, 1), MaritalStatus.SINGLE);
        dataPersonal1.setUser(administrator);
        dataPersonal1.setUserCreation(administrator.getName());
        dataPersonal.setUser(user);
        dataPersonal.setUserCreation(user.getName());
        dataPersonalRepository.saveAll(Arrays.asList(dataPersonal, dataPersonal1));

        // Telefone
        Telephone telephone = new Telephone("55", "11", "993527709", dataPersonal);
        Telephone telephone1 = new Telephone("55", "21", "987392918", dataPersonal1);
        telephone1.setUser(administrator);
        telephone1.setUserCreation(administrator.getName());
        telephone.setUser(user);
        telephone.setUserCreation(user.getName());
        telephoneRepository.saveAll(Arrays.asList(telephone, telephone1));

        // Email
        Email email = new Email("viniciusmls@outlook.com", dataPersonal);
        Email email1 = new Email("nicolas@gmail.com", dataPersonal1);
        email.setUser(user);
        email.setUserCreation(user.getName());
        email1.setUser(administrator);
        email1.setUserCreation(administrator.getName());
        emailRepository.saveAll(Arrays.asList(email, email1));

        // Curso
        Course course = new Course("Java e Quarkus", "Test", BigDecimal.valueOf(20.00),
                LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 20),
                LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 20),
                "logo_imagem", StatusCourse.CONCLUSION, LevelCourse.ADVANCED);
        Course course1 = new Course("Spring JPA", "Test", BigDecimal.valueOf(30.00),
                LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 20),
                LocalDate.of(2022, 4, 1), LocalDate.of(2022, 6, 20),
                "logo_imagem", StatusCourse.PROGRESS, LevelCourse.BASIC);
        course.setUser(user);
        course.setUserCreation(user.getName());
        course1.setUser(administrator);
        course1.setUserCreation(administrator.getName());
        courseRepository.saveAll(Arrays.asList(course, course1));

        // Framework
        Framework framework = new Framework("Quarkus", "Curso de Quarkus" ,course);
        Framework framework1 = new Framework("Spring", "Spring Melhor Framework" ,course1);
        framework.setUser(user);
        framework.setUserCreation(user.getName());
        framework1.setUser(administrator);
        framework1.setUserCreation(administrator.getName());
        frameworkRepository.saveAll(Arrays.asList(framework, framework1));

        // Linguagem
        Language language = new Language("Ingles", Level.BASIC);
        Language language1 = new Language("Portugues", Level.INTERMEDIARY);
        language.setUser(user);
        language1.setUser(administrator);
        language.setUserCreation(user.getName());
        language1.setUserCreation(administrator.getName());
        languageRepository.saveAll(Arrays.asList(language, language1));

        // Part
        Part part = new Part("POO", Level.INTERMEDIARY, language);
        Part part1 = new Part("THREAD", Level.ADVANCED, language1);
        part.setUser(user);
        part.setUserCreation(user.getName());
        part1.setUser(administrator);
        part1.setUserCreation(administrator.getName());
        partRepository.saveAll(Arrays.asList(part, part1));

        // Linguagem de Programacao
        LanguageProgrammer languageProgrammer = new LanguageProgrammer("Java",
                "Linguagem Orientada a Objetos", course);
        languageProgrammer.setUser(user);
        languageProgrammer.setUserCreation(user.getName());
        LanguageProgrammer languageProgrammer1 = new LanguageProgrammer("C#",
                "Linguagem Orientada a Objetos", course1);
        languageProgrammer1.setUser(administrator);
        languageProgrammer1.setUserCreation(administrator.getName());
        languageProgrammerRepository.saveAll(Arrays.asList(languageProgrammer, languageProgrammer1));

        // Hard Skills
        HardSkills hardSkills = new HardSkills("Conhecimento em programar Java",
                Level.ADVANCED, languageProgrammer, framework);
        hardSkills.setUser(user);
        hardSkills.setUserCreation(user.getName());
        HardSkills hardSkills1 = new HardSkills("Conhecimento em programar C#",
                Level.ADVANCED, languageProgrammer1, framework1);
        hardSkills1.setUser(administrator);
        hardSkills1.setUserCreation(administrator.getName());
        hardSkillsRepository.saveAll(Arrays.asList(hardSkills, hardSkills1));

        // Certificacao
        Certificate certificate = new Certificate("ITIL V4", "http://teste.com/teste.png", partner);
        certificate.setUser(user);
        certificate.setUserCreation(user.getName());
        Certificate certificate1 = new Certificate("JAVA Programmer", "http://teste.com/teste.png", partner1);
        certificate1.setUser(administrator);
        certificate1.setUserCreation(administrator.getName());
        certificateRepository.saveAll(Arrays.asList(certificate, certificate1));

        // Office
        Office office = new Office("Desenvolvedor de Software Java", "teste", OfficeLevel.JUNIOR);
        Office office1 = new Office("Suporte de TI", "teste", OfficeLevel.INTERNSHIP);
        office.setUser(user);
        office.setUserCreation(user.getName());
        office1.setUser(administrator);
        office1.setUserCreation(administrator.getName());
        officeRepository.saveAll(Arrays.asList(office, office1));

        // SoftSkills
        SoftSkills softSkills = new SoftSkills("Comunicacao Assertiva", true);
        SoftSkills softSkills1 = new SoftSkills("Boa Escrita", false);
        softSkills.setUser(user);
        softSkills.setUserCreation(user.getName());
        softSkills1.setUser(administrator);
        softSkills1.setUserCreation(administrator.getName());
        softSkillsRepository.saveAll(Arrays.asList(softSkills, softSkills1));

        // Graduacao
        Graduation graduation = new Graduation("Gestao de Tecnologia da Informacao", 2400, LocalDate.of(2014, 1, 1),
                LocalDate.of(2016, 6, 30), "Sao Paulo", BigDecimal.valueOf(9),
                SituationGraduation.CONCLUSION, TypeGraduation.TECHNOLOGIST, partner);
        Graduation graduation1 = new Graduation("Gestao Estrategica de Tecnologia da Informacao", 130, LocalDate.of(2018, 1, 1),
                LocalDate.of(2020, 12, 15), "Braganca Paulista", BigDecimal.valueOf(10.0),
                SituationGraduation.NOT_CONCLUSION, TypeGraduation.POST_DOCTORAL, partner1);
        graduation.setUser(user);
        graduation.setUserCreation(user.getName());
        graduation1.setUser(administrator);
        graduation1.setUserCreation(administrator.getName());
        graduationRepository.saveAll(Arrays.asList(graduation1, graduation));

        // Material
        Material material = new Material("atividade1", part);
        Material material1 = new Material("atividade2", part1);
        material.setUser(user);
        material.setUserCreation(user.getName());
        material1.setUser(administrator);
        material1.setUserCreation(administrator.getName());
        materialRepository.saveAll(Arrays.asList(material, material1));

        // Objetivos Profissionais
        ProfessionalGoal professionalGoal = new ProfessionalGoal("Desenvolvedor Pleno", "TESTE");
        ProfessionalGoal professionalGoal1 = new ProfessionalGoal("Tech Lead", "TESTE");
        professionalGoal.setUser(user);
        professionalGoal.setUserCreation(user.getName());
        professionalGoal1.setUser(administrator);
        professionalGoal1.setUserCreation(administrator.getName());
        professionalGoalRepository.saveAll(Arrays.asList(professionalGoal, professionalGoal1));

        // Disciplina da Graduacao
        Subject subject = new Subject("POO", 40, BigDecimal.valueOf(9.9), "teste", "periodo", SituationSubject.APPROVED,
                graduation);
        Subject subject1 = new Subject("C#", 40, BigDecimal.valueOf(8.9), "teste", "periodo", SituationSubject.APPROVED,
                graduation1);
        subject.setUser(user);
        subject.setUserCreation(user.getName());
        subject1.setUser(administrator);
        subject1.setUserCreation(administrator.getName());
        subjectRepository.saveAll(Arrays.asList(subject,subject1));

        // Experiencia Profissional Resumido
        ProfessionalExperienceResume professionalExperienceResume = new ProfessionalExperienceResume(LocalDate.of(2014, 1, 1), LocalDate.of(2014, 1, 1));
        professionalExperienceResume.setUser(user);
        professionalExperienceResume.setUserCreation(user.getName());
        professionalExperienceResumeRepository.save(professionalExperienceResume);

        // Experiencia Profissional
        ProfessionalExperience professionalExperience = new ProfessionalExperience(LocalDate.of(2014, 1, 1),
                LocalDate.of(2014, 1, 1), OfficeEnum.CLT, StatusWork.CURRENT, partner, office,
                professionalExperienceResume);
        professionalExperience.setUser(user);
        professionalExperience.setUserCreation(user.getName());
        professionalExperienceRepository.save(professionalExperience);

        // Atividade da Experiencia Profissional
        Assignments assignments = new Assignments("Desenvolver aplicativos", professionalExperience);
        Assignments assignments1 = new Assignments("Criar Manuais", professionalExperience);
        assignments.setUser(user);
        assignments.setUserCreation(user.getName());
        assignments1.setUserCreation(user.getName());
        assignments1.setUser(user);
        assignmentsRepository.saveAll(Arrays.asList(assignments1, assignments));

        // Plano de Estudos
        StudyPlan studyPlan = new StudyPlan("Tech Lead", Level.ADVANCED, languageProgrammer, framework);
        studyPlan.setUser(user);
        studyPlan.setUserCreation(user.getName());
        studyPlanRepository.saveAll(Arrays.asList(studyPlan));

        // Tópico do Plano de Estudos
        Topic topic = new Topic("Ser um excelente Tech Lead", studyPlan);
        topic.setUser(user);
        topic.setUserCreation(user.getName());
        topicRepository.save(topic);

        Activities activities = new Activities(LocalDate.now(), "Novo Curso", course);
        activities.setUser(user);
        activities.setUserCreation(user.getName());
        activitiesRepository.save(activities);
    }
}