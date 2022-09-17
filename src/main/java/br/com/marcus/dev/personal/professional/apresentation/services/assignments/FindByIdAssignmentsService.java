package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdAssignmentsService {

    @Autowired private AssignmentsRepository assignmentsRepository;

    public Assignments findByIdEntity(UUID id){
        Optional<Assignments> optionalAssignments = assignmentsRepository.findById(id);
        if (optionalAssignments.isEmpty()){
            throw new ResourceNotFoundException("ASSIGNMENTS NOT FOUND EXCEPTION");
        }
        if (!optionalAssignments.get().isStatus()){
            throw new ResourceNotFoundException("ASSIGNMENTS NOT FOUND EXCEPTION");
        }
        return optionalAssignments.get();
    }
}
