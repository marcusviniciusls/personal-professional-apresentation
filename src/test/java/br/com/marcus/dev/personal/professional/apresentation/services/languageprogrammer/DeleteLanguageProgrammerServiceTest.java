package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
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
public class DeleteLanguageProgrammerServiceTest {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private DeleteLanguageProgrammerService deleteLanguageProgrammerService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        LanguageProgrammer languageProgrammer = new LanguageProgrammer(id, "Java", "");
        languageProgrammerRepository.save(languageProgrammer);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Language Programmer")
    public void deleteTest(){
        // Executar método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        deleteLanguageProgrammerService.delete(id);
        // Teste Unitário
        Optional<LanguageProgrammer> response = languageProgrammerRepository.findById(id);
        Assertions.assertTrue(response.isEmpty());
    }
}
