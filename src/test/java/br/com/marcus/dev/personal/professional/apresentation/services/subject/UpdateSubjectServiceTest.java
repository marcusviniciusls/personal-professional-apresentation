package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormOnlySave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SubjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateSubjectServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private GraduationRepository graduationRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private UpdateSubjectService updateSubjectService;
    @Autowired private SubjectRepository subjectRepository;

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
        graduation.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        Graduation graduation1 = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation1.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372"));
        graduationRepository.save(graduation);
        graduationRepository.save(graduation1);

        Subject subject = new Subject("Programacao Web", BigDecimal.valueOf(10), BigDecimal.valueOf(9.6)
                , "description", "01/2022", "imagem/record", SituationSubject.APPROVED
                , graduation);
        subject.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        subject.setUrlImage("");
        subjectRepository.save(subject);

        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(subject);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Subject")
    public void updateSubject(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        SubjectFormUpdate subjectFormUpdate = new SubjectFormUpdate("Programa Web", "description", "01/2022", 1, id);
        SubjectResponse response = updateSubjectService.update(subjectFormUpdate, UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));

        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Programacao Web", response.getName());
        Assertions.assertEquals(BigDecimal.valueOf(10), response.getQtdHours());
        Assertions.assertEquals(BigDecimal.valueOf(9.6), response.getNote());
        Assertions.assertEquals("description", response.getDescription());
        Assertions.assertEquals("01/2022", response.getPeriod());
        Assertions.assertEquals("imagem/record", response.getImageReportRecord());
        Assertions.assertEquals(SituationSubject.APPROVED, response.getSituationSubject());
    }
}
