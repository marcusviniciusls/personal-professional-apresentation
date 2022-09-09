package br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalGoalFactory {

    @Autowired private ModelMapper modelMapper;

    public ProfessionalGoalResponse convertEntityInResponse(ProfessionalGoal professionalGoal){
        return modelMapper.map(professionalGoal, ProfessionalGoalResponse.class);
    }
}
