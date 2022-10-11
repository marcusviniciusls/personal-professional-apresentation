package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllFrameworkServiceTest {

    @Autowired private FindAllFrameworkService findAllFrameworkService;
    @Autowired private FrameworkRepository frameworkRepository;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        Framework framework = new Framework(id, "Quarkus", "description", "url");
        frameworkRepository.save(framework);
    }

    @Test
    @DisplayName("Buscar Todos os Frameworks")
    public void findAllTest(){
        Page<FrameworkResponse> page = findAllFrameworkService.findAll(PageRequest.of(1,1));
        Assertions.assertEquals(1, page.getSize());
    }
}
