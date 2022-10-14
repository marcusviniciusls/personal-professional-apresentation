package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
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
public class FindByIdSubjectServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private FindByIdSubject findByIdSubject;

    @BeforeEach
    public void setupInit(){
        // Preparacao do ambiente
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        BranchActivity branchActivity = new BranchActivity("School");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partnerRepository.save(partner);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduationRepository.save(graduation);

        Subject subject = new Subject("Programacao Web", BigDecimal.valueOf(10), BigDecimal.valueOf(9.6)
                , "description", "01/2022", "imagem/record", SituationSubject.APPROVED
                , graduation);
        subject.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        subject.setUrlImage("imagem/teste");
        subjectRepository.save(subject);

        Subject subjectStatusFalse = new Subject("Programacao Web", BigDecimal.valueOf(10), BigDecimal.valueOf(9.6)
                , "description", "01/2022", "imagem/record", SituationSubject.APPROVED
                , graduation);
        subjectStatusFalse.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"));
        subjectStatusFalse.setUrlImage("imagem/teste");
        subjectStatusFalse.setStatus(false);
        subjectRepository.save(subjectStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Subject por Id")
    public void findByIdEntityTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Subject subject = findByIdSubject.findByIdEntity(id);
        // Testes Unitários
        Assertions.assertTrue(subject != null);
        Assertions.assertEquals("Programacao Web", subject.getName());
        Assertions.assertEquals(BigDecimal.valueOf(10), subject.getQtdHours());
        Assertions.assertEquals(BigDecimal.valueOf(9.6), subject.getNote());
        Assertions.assertEquals("description", subject.getDescription());
        Assertions.assertEquals("01/2022", subject.getPeriod());
        Assertions.assertEquals("imagem/record", subject.getImageReportRecord());
        Assertions.assertEquals(SituationSubject.APPROVED, subject.getSituationSubject());
        Assertions.assertTrue(subject.getGraduation() != null);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Subject por Id - Status False")
    public void findByIdEntityStatusFalseTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdSubject.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Subject por Id - Not Found")
    public void findByIdEntityNotFoundTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff311");
        // Teste Unitário
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdSubject.findByIdEntity(id));
    }
}
