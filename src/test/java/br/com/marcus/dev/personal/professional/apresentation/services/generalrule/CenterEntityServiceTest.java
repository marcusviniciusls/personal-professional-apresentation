package br.com.marcus.dev.personal.professional.apresentation.services.generalrule;

import br.com.marcus.dev.personal.professional.apresentation.config.security.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CenterEntityServiceTest {

    @Autowired private CenterEntityService centerEntityService;
    @MockBean private UserService userService;

    @Test
    @DisplayName("Setar para Salvar")
    public void setDataToSaveTest(){
        centerEntityService.setDataToSave()
    }
}
