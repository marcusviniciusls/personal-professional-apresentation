package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
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

import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteImageFrameworkServiceTest {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private DeleteImageFrameworkService deleteImageFrameworkService;
    @MockBean private CenterEntityService centerEntityService;
    @MockBean private DeleteFileService deleteFileService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework = new Framework(id, "Quarkus", "description", "");
        frameworkRepository.save(framework);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(framework);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        BDDMockito.given(deleteFileService.deleteObjectS3(Mockito.any(String.class))).willReturn(true);
    }

    @Test
    @DisplayName("Apagar Imagem com sucesso")
    public void deleteTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        deleteImageFrameworkService.deleteImageS3(id);
        // Buscar no Banco de Dados
        Optional<Framework> optionalFramework = frameworkRepository.findById(id);
        Assertions.assertEquals("", optionalFramework.get().getUrlImage());
    }
}
