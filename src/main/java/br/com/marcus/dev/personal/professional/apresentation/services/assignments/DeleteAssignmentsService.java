package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteAssignmentsService {

    @Autowired private FindByIdAssignmentsService findByIdAssignmentsService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Assignments assignments = findByIdAssignmentsService.findByIdEntity(id);
        try{
            assignmentsRepository.delete(assignments);
        } catch(DataIntegrityViolationException ex){
            assignments = (Assignments) centerEntityService.setDataToDelete(assignments);
            assignmentsRepository.save(assignments);
        }
    }
}
