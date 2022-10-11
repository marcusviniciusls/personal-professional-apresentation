package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllListFrameworkServiceTest {

    @Autowired private FindAllListFrameworkService findAllListFrameworkService;
    @Autowired private FrameworkRepository frameworkRepository;
    @MockBean private CenterEntityService centerEntityService;

    @Test
    @DisplayName("Buscar todos os Frameworks por listas")
    public void findAllFrameworkTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework1 = new Framework(id, "Quarkus", "description", "url");
        frameworkRepository.save(framework1);
        BDDMockito.given(centerEntityService.setDataToSave(Mockito.any(SuperEntity.class))).willReturn(framework1);
        List<ListFramework> listIdFramework = new ArrayList<>();
        ListFramework listFramework = new ListFramework(id);
        listIdFramework.add(listFramework);
        List<Framework> listFrameworkEntity = findAllListFrameworkService.findAllFramework(listIdFramework);
        Assertions.assertEquals(1, listFrameworkEntity.size());
    }
}
