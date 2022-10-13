package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerUpdateFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UpdateLanguageProgrammerServiceTest {

    @MockBean private CenterEntityService centerEntityService;
    @Autowired private UpdateLanguageProgrammerService updateLanguageProgrammerService;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    @Test
    @Transactional
    @DisplayName("Atualizar uma Linguagem com sucesso")
    public void updateTest(){
        // Dados Mockados
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        LanguageProgrammer languageProgrammer = new LanguageProgrammer(id, "Java", "teste");
        languageProgrammerRepository.save(languageProgrammer);
        BDDMockito.given(centerEntityService.setDataToUpdate(Mockito.any(SuperEntity.class))).willReturn(languageProgrammer);
        BDDMockito.given(centerEntityService.isStatusSuperEntity(Mockito.any(SuperEntity.class))).willReturn(true);
        // Executar método
        LanguageProgrammerUpdateFrom languageProgrammerUpdateFrom = new LanguageProgrammerUpdateFrom("Java", "teste");
        LanguageProgrammerResponse response = updateLanguageProgrammerService.update(id, languageProgrammerUpdateFrom);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Java", response.getName());
        Assertions.assertEquals("teste", response.getDescription());
        Assertions.assertTrue(response.getId() != null);
    }
}
