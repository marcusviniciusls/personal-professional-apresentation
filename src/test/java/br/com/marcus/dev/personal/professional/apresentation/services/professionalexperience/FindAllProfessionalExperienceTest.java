package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
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
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllProfessionalExperienceTest {

    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private FindAllProfessionalExperienceService findAllProfessionalExperienceService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalExperience professionalExperience = new ProfessionalExperience(id);
        professionalExperienceRepository.save(professionalExperience);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Professional Experience")
    public void findAllTest(){
        Page<ProfessionalExperienceResponse> response =  findAllProfessionalExperienceService.findAll(PageRequest.of(1,1));
        Assertions.assertTrue(response != null);
    }
}
