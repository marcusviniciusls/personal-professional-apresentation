package br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssignmentsFactory {

    @Autowired private ModelMapper modelMapper;

    public Assignments convertSaveFormInEntity(AssignmentsSaveForm assignmentsSaveForm){
        return modelMapper.map(assignmentsSaveForm, Assignments.class);
    }

    public AssignmentsResponse convertEntityInResponse(Assignments assignments){
        return modelMapper.map(assignments, AssignmentsResponse.class);
    }
}
