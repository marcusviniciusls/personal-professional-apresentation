package br.com.marcus.dev.personal.professional.apresentation.services.softskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.ActivitiesRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.FindAllSoftSkillsService;
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

import javax.transaction.Transactional;
import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllSoftSkillsServiceTest {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private FindAllSoftSkillsService findAllSoftSkillsService;
    @Autowired private ActivitiesRepository activitiesRepository;

    @BeforeEach
    public void setupInit(){
        SoftSkills softSkills1 = new SoftSkills("Boa comunicacao", true);
        SoftSkills softSkills2 = new SoftSkills("Boa escrita", true);
        SoftSkills softSkills3 = new SoftSkills("Chega no Horário", false);
        SoftSkills softSkills4 = new SoftSkills("Amigavelo", true);
        SoftSkills softSkills5 = new SoftSkills("Comunicador", false);

        softSkillsRepository.saveAll(Arrays.asList(softSkills1, softSkills2, softSkills3, softSkills4, softSkills5));
    }

    @Test
    @Transactional
    @DisplayName("Buscar todos os SoftSkills")
    public void findAllTest(){
        // Executando método
        Page<SoftSkillsResponse> response = findAllSoftSkillsService.findAll(PageRequest.of(1,3));
        // Testes Unitários
        Assertions.assertEquals(3, response.getSize());
    }
}
