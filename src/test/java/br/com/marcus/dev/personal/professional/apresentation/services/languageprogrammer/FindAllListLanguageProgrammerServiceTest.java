package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllListLanguageProgrammerServiceTest {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private FindAllListLanguageProgrammerService findAllListLanguageProgrammerService;

    @Test
    @Transactional
    @DisplayName("Listar todos as Languagens Programmer")
    public void findAllTest(){
        // Preparacao do ambiente
        UUID id1 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        UUID id2 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        UUID id3 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        UUID id4 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        UUID id5 = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff375");
        LanguageProgrammer languageProgrammer1 = new LanguageProgrammer(id1, "Java", "");
        LanguageProgrammer languageProgrammer2 = new LanguageProgrammer(id2, "C#", "");
        LanguageProgrammer languageProgrammer3 = new LanguageProgrammer(id3, "HTML", "");
        LanguageProgrammer languageProgrammer4 = new LanguageProgrammer(id4, "CSS", "");
        LanguageProgrammer languageProgrammer5 = new LanguageProgrammer(id5, "Java Script", "");
        languageProgrammerRepository.saveAll(Arrays.asList(languageProgrammer1, languageProgrammer2
                , languageProgrammer3, languageProgrammer4, languageProgrammer5));
        List<ListLanguageProgrammer> listIdLanguageProgrammer = new ArrayList<>();
        listIdLanguageProgrammer.add(new ListLanguageProgrammer(id1));
        listIdLanguageProgrammer.add(new ListLanguageProgrammer(id2));
        listIdLanguageProgrammer.add(new ListLanguageProgrammer(id3));
        listIdLanguageProgrammer.add(new ListLanguageProgrammer(id4));
        listIdLanguageProgrammer.add(new ListLanguageProgrammer(id5));

        // Executando método
        List<LanguageProgrammer> list = findAllListLanguageProgrammerService.findAllLanguageProgrammer(listIdLanguageProgrammer);

        // Teste Unitario
        Assertions.assertEquals(5, list.size());
    }
}
