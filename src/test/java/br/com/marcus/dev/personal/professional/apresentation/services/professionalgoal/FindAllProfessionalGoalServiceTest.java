package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
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
public class FindAllProfessionalGoalServiceTest {

    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private FindAllProfessionalGoalService findAllProfessionalGoalService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalGoal professionalGoal =
                new ProfessionalGoal("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        professionalGoal.setId(id);
        professionalGoalRepository.save(professionalGoal);
    }

    @Test
    @Transactional
    @DisplayName("Buscar todos os Professional Goal")
    public void findAllTest(){
        Page<ProfessionalGoalResponse> response = findAllProfessionalGoalService.findAll(PageRequest.of(1,1));
        Assertions.assertEquals(1, response.getSize());
    }
}
