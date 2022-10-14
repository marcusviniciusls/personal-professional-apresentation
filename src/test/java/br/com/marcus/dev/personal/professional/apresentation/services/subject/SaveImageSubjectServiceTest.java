package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
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
public class SaveImageSubjectServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private GraduationRepository graduationRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SendFileAwsS3 sendFileAwsS3;
    @Autowired private SaveImageSubjectService saveImageSubjectService;

    @Test
    @DisplayName("Salvar uma imagem de Subject com sucesso")
    public void saveimageTest() throws URISyntaxException {
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
        subject.setUrlImage("");
        subjectRepository.save(subject);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(subject);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Executar método
        saveImageSubjectService.saveimage(multipartFile, UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        // Testes Unitários
        Optional<Subject> optionalSubject = subjectRepository.findById(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        Assertions.assertTrue(!optionalSubject.get().getUrlImage().equals(""));
    }

    @Test
    @DisplayName("Salvar Imagem Subject com erro")
    public void saveImageSubjectErrorTest() throws URISyntaxException {
        // Dados de Entradas
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff341");
        LocalDate dateInit = LocalDate.of(2014, 1, 1);
        LocalDate dateFinish = LocalDate.of(2016, 6, 30);
        BranchActivity branchActivity = new BranchActivity("Escola Americana");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partnerRepository.save(partner);
        Graduation graduation = new Graduation("Gestao TI", BigDecimal.valueOf(100), dateInit,
                dateFinish, "Sao Paulo", BigDecimal.valueOf(9.9), SituationGraduation.CONCLUSION,
                TypeGraduation.GRADUATION, partner);
        graduation.setId(id);
        graduation.setUrlUniversityDegree("teste/teste");
        graduationRepository.save(graduation);
        Subject subject = new Subject("Programacao Web", BigDecimal.valueOf(10), BigDecimal.valueOf(9.6)
                , "description", "01/2022", "imagem/record", SituationSubject.APPROVED
                , graduation);
        subject.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        subject.setUrlImage("imagem/teste");
        subjectRepository.save(subject);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(subject);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        // Dados de Entrada
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Testes Unitários
        Assertions.assertThrows(FileException.class, () -> saveImageSubjectService.saveimage(multipartFile, UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371")));
    }
}
