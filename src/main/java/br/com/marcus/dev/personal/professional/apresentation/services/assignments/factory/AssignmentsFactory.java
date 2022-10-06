package br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveAddForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsUpdateForm;
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

    public Assignments convertSaveFormAddInEntity(AssignmentsSaveAddForm assignmentsSaveAddForm){
        return modelMapper.map(assignmentsSaveAddForm, Assignments.class);
    }

    public Assignments convertUpdateFormInEntity(AssignmentsUpdateForm assignmentsUpdateForm, Assignments assignments){
        if (assignmentsUpdateForm.getDescription() != null && !assignmentsUpdateForm.getDescription().equals("")){
            assignments.setDescription(assignmentsUpdateForm.getDescription());
        }

        return assignments;
    }
}
