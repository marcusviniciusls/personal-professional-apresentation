package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalGoalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindByIdProfessionalGoalServiceTest {

    @Autowired private ProfessionalGoalRepository professionalGoalRepository;
    @Autowired private FindByIdProfessionalGoalService findByIdProfessionalGoalService;

    @BeforeEach
    public void setupInit(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalGoal professionalGoal =
                new ProfessionalGoal("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        professionalGoal.setId(id);
        professionalGoalRepository.save(professionalGoal);

        UUID idStatusFalse = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        ProfessionalGoal professionalGoalStatusFalse =
                new ProfessionalGoal("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
        professionalGoalStatusFalse.setId(idStatusFalse);
        professionalGoalStatusFalse.setStatus(false);
        professionalGoalRepository.save(professionalGoalStatusFalse);
    }

    @Test
    @Transactional
    @DisplayName("Buscar Professional Goal por Id")
    public void findByIdTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalGoalResponse professionalGoalResponse = findByIdProfessionalGoalService.findById(id);
        Assertions.assertEquals("Ser o melhor Desenvolvedor Java", professionalGoalResponse.getDescription());
        Assertions.assertEquals("Desenvolvedor Java", professionalGoalResponse.getOffice());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Professional Goal por Id")
    public void findByIdEntityTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        ProfessionalGoal professionalGoal = findByIdProfessionalGoalService.findByIdEntity(id);
        Assertions.assertEquals("Ser o melhor Desenvolvedor Java", professionalGoal.getDescription());
        Assertions.assertEquals("Desenvolvedor Java", professionalGoal.getOffice());
    }

    @Test
    @Transactional
    @DisplayName("Buscar Professional Goal por Id Status False")
    public void findByIdStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalGoalService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Professional Goal por Id Status False")
    public void findByIdEntityStatusFalseTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff372");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalGoalService.findByIdEntity(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Professional Goal por Id NotFound")
    public void findByIdNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalGoalService.findById(id));
    }

    @Test
    @Transactional
    @DisplayName("Buscar Professional Goal por Id NotFound")
    public void findByIdEntityNotFoundTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff373");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> findByIdProfessionalGoalService.findByIdEntity(id));
    }
}
