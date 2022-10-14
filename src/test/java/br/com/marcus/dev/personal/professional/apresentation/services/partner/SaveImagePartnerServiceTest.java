package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveImagePartnerServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private PartnerRepository partnerRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SendFileAwsS3 sendFileAwsS3;
    @Autowired private SaveImagePartnerService saveImagePartnerService;

    @Test
    @Transactional
    @DisplayName("Salvar uma imagem de Partner com sucesso")
    public void saveimageTest() throws URISyntaxException {
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff341");
        BranchActivity branchActivity = new BranchActivity("Escola tes");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "", branchActivity, "");
        partner.setId(id);
        partnerRepository.save(partner);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(partner);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Executar método
        saveImagePartnerService.saveimage(multipartFile, id);
        // Testes Unitários
        Optional<Partner> optionalPartner = partnerRepository.findById(id);
        Assertions.assertTrue(!optionalPartner.get().getUrlImage().equals(""));
    }

    @Test
    @DisplayName("Salvar Imagem Partner com erro")
    public void saveImageGraduationErrorTest() throws URISyntaxException {
        // Dados de Entradas
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff341");
        BranchActivity branchActivity = new BranchActivity("Escola De Comunicacao");
        branchActivityRepository.save(branchActivity);
        Partner partner = new Partner("Impacta", "teste/teste", branchActivity, "");
        partner.setId(id);
        partnerRepository.save(partner);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(partner);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Testes Unitários
        Assertions.assertThrows(FileException.class, () -> saveImagePartnerService.saveimage(multipartFile, id));
    }

}
