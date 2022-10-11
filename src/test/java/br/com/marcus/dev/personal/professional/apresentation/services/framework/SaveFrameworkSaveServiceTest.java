package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.checkerframework.checker.units.qual.A;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveFrameworkSaveServiceTest {

    @Autowired private SaveFrameworkSaveService saveFrameworkSaveService;
    @Autowired private FrameworkRepository frameworkRepository;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private SendFileAwsS3 sendFileAwsS3;

    @Test
    @DisplayName("Salvar Imagem Framework")
    public void saveImageFrameworkTest() throws URISyntaxException {
        // Dados de Entradas
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework = new Framework(id, "Quarkus", "description", "");
        frameworkRepository.save(framework);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(framework);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Executar método
        saveFrameworkSaveService.saveImageFramework(multipartFile, id);
        // Testes Unitários
        Optional<Framework> optionalFramework = frameworkRepository.findById(id);
        Assertions.assertTrue(!optionalFramework.get().getUrlImage().equals(""));
    }

    @Test
    @DisplayName("Salvar Imagem Framework com erro")
    public void saveImageFrameworkErrorTest() throws URISyntaxException {
        // Dados de Entradas
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff320");
        Framework framework = new Framework(id, "Quarkus", "description", "teste");
        frameworkRepository.save(framework);
        // Dados de Entrada
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(framework);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(sendFileAwsS3.uploadFile(Mockito.any(MultipartFile.class))).willReturn(new URI("caminho/imagem.png"));
        MultipartFile multipartFile = new MockMultipartFile("teste", new byte[1]);
        // Testes Unitários
        Assertions.assertThrows(FileException.class, () -> saveFrameworkSaveService.saveImageFramework(multipartFile, id));
    }
}
