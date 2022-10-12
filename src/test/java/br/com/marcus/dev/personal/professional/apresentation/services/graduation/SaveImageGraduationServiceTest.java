package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveImageGraduationServiceTest {

    @Autowired private GraduationRepository graduationRepository;
    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SendFileAwsS3 sendFileAwsS3;
    @Autowired private SaveImageGraduationService saveImageGraduationService;

    @Test
    @DisplayName("Salvar uma imagem de Graduacao com sucesso")
    public void saveimageTes() throws URISyntaxException {
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff341");
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        BranchActivity branchActivity = new BranchActivity("Escola Testes teste teste");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partnerRepository.save(partner);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation.setId(id);
        graduationRepository.save(graduation);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(graduation);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Executar método
        saveImageGraduationService.saveimage(multipartFile, id);
        // Testes Unitários
        Optional<Graduation> optionalGraduation = graduationRepository.findById(id);
        Assertions.assertTrue(!optionalGraduation.get().getUrlUniversityDegree().equals(""));
    }

    @Test
    @DisplayName("Salvar Imagem Graduacao com erro")
    public void saveImageGraduationErrorTest() throws URISyntaxException {
        // Dados de Entradas
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff341");
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        BranchActivity branchActivity = new BranchActivity("Escola Testes teste teste teste");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partnerRepository.save(partner);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation.setId(id);
        graduation.setUrlUniversityDegree("teste/teste");
        graduationRepository.save(graduation);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(graduation);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        // Dados de Entrada
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Testes Unitários
        Assertions.assertThrows(FileException.class, () -> saveImageGraduationService.saveimage(multipartFile, id));
    }
}
