package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerSaveFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SaveLanguageProgrammerServiceTest {

    @MockBean private CenterEntityService centerEntityService;
    @Autowired private SaveLanguageProgrammerService saveLanguageProgrammerService;

    @BeforeEach
    public void setupInit(){
        // Dados Mockados
        LanguageProgrammer languageProgrammer = new LanguageProgrammer("Java", "teste");
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(languageProgrammer);
    }

    @Test
    @Transactional
    @DisplayName("Salvar uma Linguagem com sucesso")
    public void saveTest(){
        // Executando método
        LanguageProgrammerSaveFrom languageProgrammerSaveFrom = new LanguageProgrammerSaveFrom("Java", "teste");
        LanguageProgrammerResponse response = saveLanguageProgrammerService.save(languageProgrammerSaveFrom);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Java", response.getName());
        Assertions.assertEquals("teste", response.getDescription());
        Assertions.assertTrue(response.getId() != null);
    }
}
