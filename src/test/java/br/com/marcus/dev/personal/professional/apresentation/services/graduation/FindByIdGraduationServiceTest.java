package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.GraduationResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
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
public class FindByIdGraduationServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private FindByIdGraduationService findByIdGraduationService;

    @BeforeEach
    public void setupInit(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        BranchActivity branchActivity = new BranchActivity("Escola testandooooooooo");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partnerRepository.save(partner);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation.setId(id);
        graduation.setDateInitPreview(dateInit);
        graduation.setDateFinishPreview(dateFinish);
        graduation.setUrlUniversityDegree("url");
        Graduation graduationStatusFalse = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduationStatusFalse.setId(id);
        graduationStatusFalse.setDateInitPreview(dateInit);
        graduationStatusFalse.setDateFinishPreview(dateFinish);
        graduationStatusFalse.setUrlUniversityDegree("url");
        graduationStatusFalse.setId(idStatusFalse);
        graduationStatusFalse.setStatus(false);
        graduationRepository.save(graduation);
        graduationRepository.save(graduationStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Graduacao por Id com sucesso")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        GraduationResponse graduationResponse = findByIdGraduationService.findById(id);

        // Testes Unitários
        Assertions.assertTrue(graduationResponse != null);
        Assertions.assertEquals("Gestao TI", graduationResponse.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), graduationResponse.getQtdHours());
        Assertions.assertEquals(LocalDate.of(2014, 1, 1), graduationResponse.getDateInitPreview());
        Assertions.assertEquals(LocalDate.of(2016, 6, 30), graduationResponse.getDateFinishPreview());
        Assertions.assertEquals("Sao Paulo", graduationResponse.getLocation());
        Assertions.assertEquals(BigDecimal.valueOf(9.9), graduationResponse.getNoteFinish());
        Assertions.assertEquals(LocalDate.of(2014, 1, 1), graduationResponse.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2016, 6, 30), graduationResponse.getDateFinishReal());
        Assertions.assertEquals("url", graduationResponse.getUrlUniversityDegree());
        Assertions.assertEquals(SituationGraduation.CONCLUSION, graduationResponse.getSituationGraduation());
        Assertions.assertEquals(TypeGraduation.GRADUATION, graduationResponse.getTypeGraduation());
        Assertions.assertTrue(graduationResponse.getPartnerResponse() != null);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Graduacao por Id com sucesso")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        Graduation graduation = findByIdGraduationService.findByIdEntity(id);

        // Testes Unitários
        Assertions.assertTrue(graduation != null);
        Assertions.assertEquals("Gestao TI", graduation.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), graduation.getQtdHours());
        Assertions.assertEquals(LocalDate.of(2014, 1, 1), graduation.getDateInitPreview());
        Assertions.assertEquals(LocalDate.of(2016, 6, 30), graduation.getDateFinishPreview());
        Assertions.assertEquals("Sao Paulo", graduation.getLocation());
        Assertions.assertEquals(BigDecimal.valueOf(9.9), graduation.getNoteFinish());
        Assertions.assertEquals(LocalDate.of(2014, 1, 1), graduation.getDateInitReal());
        Assertions.assertEquals(LocalDate.of(2016, 6, 30), graduation.getDateFinishReal());
        Assertions.assertEquals("url", graduation.getUrlUniversityDegree());
        Assertions.assertEquals(SituationGraduation.CONCLUSION, graduation.getSituationGraduation());
        Assertions.assertEquals(TypeGraduation.GRADUATION, graduation.getTypeGraduation());
        Assertions.assertTrue(graduation.getPartner() != null);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Graduacao por Id e nao achar porque o status está false")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        // Testes Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdGraduationService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Graduacao por Id e nao achar porque o status está false")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff376");
        // Testes Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdGraduationService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Graduacao por Id e nao achar porque nao existe com esse Id")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        // Testes Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdGraduationService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Graduacao por Id e nao achar porque nao existe com esse Id")
    public void findByIdEntityNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff303");
        // Testes Unitários
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdGraduationService.findByIdEntity(id));
    }
}
