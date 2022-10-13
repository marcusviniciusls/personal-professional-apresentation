package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdLanguageProgrammerServiceTest {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;

    @BeforeEach
    public void setupInit(){
        // Preparacao Ambiente
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        LanguageProgrammer languageProgrammer = new LanguageProgrammer(id, "Java", "");
        languageProgrammerRepository.save(languageProgrammer);

        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        LanguageProgrammer languageProgrammerStatusFalse = new LanguageProgrammer(idStatusFalse, "Java", "");
        languageProgrammerStatusFalse.setStatus(false);
        languageProgrammerRepository.save(languageProgrammerStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id o LanguageProgrammer com sucesso")
    public void findByIdTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        LanguageProgrammerResponse response = findByIdLanguageProgrammerService.findById(id);
        // Testes Unitários
        Assertions.assertTrue(response != null);
        Assertions.assertEquals("Java", response.getName());
        Assertions.assertEquals(id, response.getId());
        Assertions.assertEquals("", response.getDescription());
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id o LanguageProgrammer com sucesso")
    public void findByIdEntityTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff370");
        LanguageProgrammer languageProgrammer = findByIdLanguageProgrammerService.findByIdEntity(id);
        // Testes Unitários
        Assertions.assertTrue(languageProgrammer != null);
        Assertions.assertEquals("Java", languageProgrammer.getName());
        Assertions.assertEquals(id, languageProgrammer.getId());
        Assertions.assertEquals("", languageProgrammer.getDescription());
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id o LanguageProgrammer - Status False")
    public void findByIdStatusFalseTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageProgrammerService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id o LanguageProgrammer Status False")
    public void findByIdEntityStatusFalseTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageProgrammerService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id o LanguageProgrammer - Not Found")
    public void findByIdNotFoundTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageProgrammerService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar por Id o LanguageProgrammer Not Found")
    public void findByIdEntityNotFoundTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdLanguageProgrammerService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Verificar se a LanguageProgrammer está com o status True")
    public void findByIdNotErrorTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        Optional<LanguageProgrammer> check = findByIdLanguageProgrammerService.findByIdNotError(id);
        Assertions.assertTrue(check.isPresent());
    }
}
