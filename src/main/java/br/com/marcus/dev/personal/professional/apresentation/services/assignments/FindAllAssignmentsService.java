package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindAllAssignmentsService {

    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private AssignmentsFactory assignmentsFactory;

    public Page<AssignmentsResponse> findAll(Pageable pageable, UUID idProfessionalExperience){
        Page<Assignments> pageAssignments = assignmentsRepository.findAll(pageable, idProfessionalExperience);
        Page<AssignmentsResponse> pageAssignmentsResponse = pageAssignments.map(a -> assignmentsFactory.convertEntityInResponse(a));
        return pageAssignmentsResponse;
    }
}
