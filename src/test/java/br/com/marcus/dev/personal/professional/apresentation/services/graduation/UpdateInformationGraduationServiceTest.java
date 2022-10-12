package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

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
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateInformationGraduationServiceTest {

    @Autowired private UpdateInformationGraduationService updateInformationGraduationService;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private SubjectRepository subjectRepository;

    @Test
    @Transactional
    @DisplayName("Atualizar Informacoes da Graduacao")
    public void updateToSaveTest(){
        // Preparacao para o teste
        BranchActivity branchActivity = new BranchActivity("Escolar");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partnerRepository.save(partner);
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduationRepository.save(graduation);
        Subject subject1 = new Subject("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(9.0), "description", "01/2014", "", SituationSubject.APPROVED,
                graduation);
        Subject subject2 = new Subject("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(8.5), "description", "01/2014", "", SituationSubject.APPROVED,
                graduation);
        Subject subject3 = new Subject("HTML e CSS", BigDecimal.valueOf(10)
                , BigDecimal.valueOf(8.0), "description", "01/2014", "", SituationSubject.APPROVED,
                graduation);
        graduation.addListSubject(subject1);
        graduation.addListSubject(subject2);
        graduation.addListSubject(subject3);
        subjectRepository.saveAll(Arrays.asList(subject1, subject2, subject3));

        // Dados mockados
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(graduation);

        // Executando método
        Graduation graduationNew = updateInformationGraduationService.updateToSave(graduation);

        // Testes Unitários
        Assertions.assertEquals(BigDecimal.valueOf(8.5), graduationNew.getNoteFinish());
        Assertions.assertEquals(BigDecimal.valueOf(30), graduationNew.getQtdHours());
    }
}
