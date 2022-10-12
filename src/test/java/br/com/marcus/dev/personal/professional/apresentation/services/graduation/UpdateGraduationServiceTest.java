package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.GraduationResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorDateInitAfterDateFinish;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateGraduationServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @MockBean private CenterEntityService centerEntityService;
    @Autowired private UpdateGraduationService updateGraduationService;
    @Autowired private GraduationRepository graduationRepository;

    @Test
    @Transactional
    @DisplayName("Atualizar Graduacao por Id com sucesso")
    public void updateTest(){
        // Preparacao para o teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        BranchActivity branchActivity = new BranchActivity("Escolar teste test");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partner.setId(id);
        partnerRepository.save(partner);
        // Dados mockados
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation.setId(id);
        graduation.setDateInitPreview(dateInit);
        graduation.setDateFinishPreview(dateFinish);
        graduation.setUrlUniversityDegree("url");
        graduation.setStatus(true);
        graduationRepository.save(graduation);

        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(graduation);
        // Dados de Entrada
        GraduationFormUpdate graduationFormUpdate = new GraduationFormUpdate("Gestao de TI"
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , "Sao Paulo", 4, id);

        // Executar métodos
        GraduationResponse response = updateGraduationService.update(graduationFormUpdate, id);

        // Teste Unitário
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Gestao TI", response.getName());
        Assertions.assertEquals(LocalDate.of(2014, 1, 1), response.getDateInitPreview());
        Assertions.assertEquals(LocalDate.of(2016, 6, 30), response.getDateFinishPreview());
        Assertions.assertEquals(LocalDate.of(2014, 1, 1), response.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2016, 6, 30), response.getDateFinishReal());
        Assertions.assertEquals(TypeGraduation.GRADUATION, response.getTypeGraduation());
        Assertions.assertTrue(response.getPartnerResponse() != null);
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Graduacao por Id com erro por causa da data inicial ser posterior a data final")
    public void updateErrorRealDateInitAfterFinishTest(){
        // Preparacao para o teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        BranchActivity branchActivity = new BranchActivity("Escolar teste test");
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
        graduation.setId(id);
        graduation.setDateInitPreview(dateInit);
        graduation.setDateFinishPreview(dateFinish);
        graduation.setUrlUniversityDegree("url");
        graduation.setStatus(true);
        graduationRepository.save(graduation);

        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(graduation);
        // Dados de Entrada
        GraduationFormUpdate graduationFormUpdate = new GraduationFormUpdate("Gestao de TI"
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2018, 1, 1), LocalDate.of(2016, 6, 30)
                , "Sao Paulo", 4, id);

        // Teste Unitário
        Assertions.assertThrows(ErrorDateInitAfterDateFinish.class, () -> updateGraduationService.update(graduationFormUpdate, id));
    }

    @Test
    @Transactional
    @DisplayName("Atualizar Graduacao por Id com erro por causa da data previsao ser posterior a data final")
    public void updateErrorPreviewDateInitAfterFinishTest(){
        // Preparacao para o teste
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        BranchActivity branchActivity = new BranchActivity("Escolar teste test");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partner.setId(id);
        partnerRepository.save(partner);
        // Dados mockados
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.NOT_CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation.setId(id);
        graduation.setDateInitPreview(dateInit);
        graduation.setDateFinishPreview(dateFinish);
        graduation.setUrlUniversityDegree("url");
        graduation.setStatus(true);
        graduationRepository.save(graduation);

        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(graduation);
        // Dados de Entrada
        GraduationFormUpdate graduationFormUpdate = new GraduationFormUpdate("Gestao de TI"
                , LocalDate.of(2018, 1, 1), LocalDate.of(2016, 6, 30)
                , LocalDate.of(2014, 1, 1), LocalDate.of(2016, 6, 30)
                , "Sao Paulo", 4, id);

        // Teste Unitário
        Assertions.assertThrows(ErrorDateInitAfterDateFinish.class, () -> updateGraduationService.update(graduationFormUpdate, id));
    }
}
