package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveAssignmentsService {

    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private CenterEntityService centerEntityService;

    public Assignments save(Assignments assignments){
        assignments = (Assignments) centerEntityService.setDataToSave(assignments);
        return assignmentsRepository.save(assignments);
    }
}
