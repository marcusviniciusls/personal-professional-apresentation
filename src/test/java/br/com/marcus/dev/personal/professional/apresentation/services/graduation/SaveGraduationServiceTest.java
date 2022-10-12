package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormSave;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorDateInitAfterDateFinish;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveGraduationServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private SaveGraduationService saveGraduationService;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SaveActivitiesService saveActivitiesService;

    @Test
    @DisplayName("Salvar uma Graduacao com sucesso")
    public void saveTest(){
        // Preparacao para o teste
        BranchActivity branchActivity = new BranchActivity("Escolar");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partner.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff392"));
        partnerRepository.save(partner);
        // Dados mockados
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);

        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(graduation);
        BDDMockito.given(saveActivitiesService.saveMovementGraduation(Mockito.any(Graduation.class))).willReturn(true);
        // Dados de Entrada
        GraduationFormSave graduationFormSave = new GraduationFormSave("Gestao de TI"
        , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
        , "Sao Paulo", 4, "cb260da4-01fb-48f0-aec4-d7f9db2ff392", "");
        SubjectFormSave subjectFormSave1 = new SubjectFormSave("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(9.0), "description", "01/2014", 1, "");
        SubjectFormSave subjectFormSave2 = new SubjectFormSave("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(9.0), "description", "01/2014", 1, "");
        SubjectFormSave subjectFormSave3 = new SubjectFormSave("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(9.0), "description", "01/2014", 1, "");
        SubjectFormSave subjectFormSave4 = new SubjectFormSave("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(9.0), "description", "01/2014", 1, "");
        SubjectFormSave subjectFormSave5 = new SubjectFormSave("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(9.0), "description", "01/2014", 1, "");
        graduationFormSave.addListSubjectFormSave(subjectFormSave1);
        graduationFormSave.addListSubjectFormSave(subjectFormSave2);
        graduationFormSave.addListSubjectFormSave(subjectFormSave3);
        graduationFormSave.addListSubjectFormSave(subjectFormSave4);
        graduationFormSave.addListSubjectFormSave(subjectFormSave5);
        // Executar o teste
        UUID id = saveGraduationService.save(graduationFormSave);
        // Teste unitário
        Assertions.assertTrue(id != null);
    }

    @Test
    @DisplayName("Data prevista Inicial depois da Data Final")
    public void datePrevInitBeforeDateFinishTest(){
        // Dados de Entrada
        GraduationFormSave graduationFormSave = new GraduationFormSave("Gestao de TI"
                , LocalDate.of(2017, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , "Sao Paulo", 4, "cb260da4-01fb-48f0-aec4-d7f9db2ff392", "");
        // Executar Dados
        Assertions.assertThrows(ErrorDateInitAfterDateFinish.class, () -> saveGraduationService.save(graduationFormSave));
    }

    @Test
    @DisplayName("Data real Inicial depois da Data Final")
    public void dateRealInitBeforeDateFinishTest(){
        // Dados de Entrada
        GraduationFormSave graduationFormSave = new GraduationFormSave("Gestao de TI"
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2018, 1, 1), LocalDate.of(2016, 6, 30)
                , "Sao Paulo", 4, "cb260da4-01fb-48f0-aec4-d7f9db2ff392", "");
        // Executar Dados
        Assertions.assertThrows(ErrorDateInitAfterDateFinish.class, () -> saveGraduationService.save(graduationFormSave));
    }
}
