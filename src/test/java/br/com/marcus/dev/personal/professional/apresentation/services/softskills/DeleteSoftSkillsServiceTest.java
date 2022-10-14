package br.com.marcus.dev.personal.professional.apresentation.services.softskills;

import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteSoftSkillsServiceTest {

    @Autowired private SoftSkillsRepository softSkillsRepository;

    @BeforeEach
    public void setupInit(){
        SoftSkills softSkills = new SoftSkills("Boa comunicacao", true);
        softSkillsRepository.save(softSkills);
    }

    @Test
    @Transactional
    @DisplayName("Apagar SoftSkills")
    public void deleteTest(){

    }
}
