package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal;

import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteProfessionalGoalServiceTest {

    @BeforeEach
    public void setupInit(){
        ProfessionalGoal professionalGoal =
                new ProfessionalGoal("Ser o melhor Desenvolvedor Java", "Desenvolvedor Java");
    }
}
