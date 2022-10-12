package br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.GraduationResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
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

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GraduationFactoryTest {

    @Autowired private GraduationFactory graduationFactory;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private GraduationRepository graduationRepository;

    @Test
    @Transactional
    @DisplayName("Converter FormSave em Entidade (Graduacao)")
    public void convertFormSaveToEntityTest(){
        // Preparacao para o teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        BranchActivity branchActivity = new BranchActivity("Escola testandooooooo");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partner.setId(id);
        partnerRepository.save(partner);
        // Dados mockados
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.IN_PROGRESS,
                TypeGraduation.GRADUATION, partner);
        graduationRepository.save(graduation);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(graduation);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        // Dados de Entrada
        GraduationFormSave graduationFormSave = new GraduationFormSave("Gestao de TI"
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2014, 1, 1)
                , "Sao Paulo", 4, "cb260da4-01fb-48f0-aec4-d7f9db2ff375", "");
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

        // Executando método
        Graduation graduationNew = graduationFactory.convertFormSaveToEntity(graduationFormSave);

        // Testes unitários
        Assertions.assertTrue(graduationNew != null);
        Assertions.assertEquals("Gestao TI", graduationNew.getName());
        Assertions.assertEquals(TypeGraduation.GRADUATION, graduationNew.getTypeGraduation());
        Assertions.assertEquals(SituationGraduation.IN_PROGRESS, graduationNew.getSituationGraduation());
    }

    @Test
    @DisplayName("Trazer a Nota Final")
    public void getNoteFinishTest(){
        Subject subject1 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(9.0), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject2 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(8.0), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject3 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(7.3), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject4 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(2.6), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject5 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(5.6), "description", "01/2014", "", SituationSubject.APPROVED);
        List<Subject> listSubject = new ArrayList<>();
        listSubject.add(subject1);
        listSubject.add(subject2);
        listSubject.add(subject3);
        listSubject.add(subject4);
        listSubject.add(subject5);

        // Executando método
        BigDecimal note = graduationFactory.getNoteFinish(listSubject);

        // Testes unitários
        Assertions.assertEquals(BigDecimal.valueOf(6.5), note);
    }

    @Test
    @DisplayName("Trazer a Quantidade de Horas Totais")
    public void getQtdHoursTest(){
        Subject subject1 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(9.0), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject2 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(8.0), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject3 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(7.3), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject4 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(2.6), "description", "01/2014", "", SituationSubject.APPROVED);
        Subject subject5 = new Subject("HTML e CSS", BigDecimal.valueOf(10), BigDecimal.valueOf(5.6), "description", "01/2014", "", SituationSubject.APPROVED);
        List<Subject> listSubject = new ArrayList<>();
        listSubject.add(subject1);
        listSubject.add(subject2);
        listSubject.add(subject3);
        listSubject.add(subject4);
        listSubject.add(subject5);

        // Executando método
        BigDecimal hours = graduationFactory.getQtdHours(listSubject);

        // Testes unitários
        Assertions.assertEquals(BigDecimal.valueOf(50), hours);
    }

    @Test
    @Transactional
    @DisplayName("Converter Entidade em DTO (Graduacao)")
    public void convertEntityInDtoTest(){
        // Preparacao para o teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        BranchActivity branchActivity = new BranchActivity("Escola testandooooooo");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partner.setId(id);
        partnerRepository.save(partner);
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.IN_PROGRESS,
                TypeGraduation.GRADUATION, partner);
        graduationRepository.save(graduation);

        // Executando método
        GraduationResponse response = graduationFactory.convertEntityInDto(graduation);

        // Testes unitários
        Assertions.assertTrue(response != null);
        Assertions.assertTrue(response.getPartnerResponse() != null);
        Assertions.assertEquals("Gestao TI", response.getName());
        Assertions.assertEquals(TypeGraduation.GRADUATION, response.getTypeGraduation());
        Assertions.assertEquals(SituationGraduation.IN_PROGRESS, response.getSituationGraduation());
    }

    @Test
    @Transactional
    @DisplayName("Converter FormUpdate em Entidade (Graduacao)")
    public void convertFormUpdateInEntityTest(){
        // Preparacao para o teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        BranchActivity branchActivity = new BranchActivity("Escola testandooooooo");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partner.setId(id);
        partnerRepository.save(partner);
        // Dados mockados
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.IN_PROGRESS,
                TypeGraduation.GRADUATION, partner);
        graduationRepository.save(graduation);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(graduation);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        // Dados de Entrada
        GraduationFormUpdate graduationFormUpdate = new GraduationFormUpdate("Gestao de TI"
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2014, 1, 1), LocalDate.of(2014, 1, 1)
                , "Sao Paulo", 4, UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375"));

        // Executando método
        Graduation graduationNew = graduationFactory.convertFormUpdateInEntity(graduationFormUpdate, graduation);

        // Testes unitários
        Assertions.assertTrue(graduationNew != null);
        Assertions.assertEquals("Gestao de TI", graduationNew.getName());
        Assertions.assertEquals(TypeGraduation.GRADUATION, graduationNew.getTypeGraduation());
        Assertions.assertEquals(SituationGraduation.IN_PROGRESS, graduationNew.getSituationGraduation());
    }
}
