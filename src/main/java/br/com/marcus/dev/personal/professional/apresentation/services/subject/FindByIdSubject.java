package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdSubject {

    @Autowired private SubjectRepository subjectRepository;
    @Autowired private CenterEntityService centerEntityService;

    public Subject findByIdEntity(UUID id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isEmpty()){
            throw new ResourceNotFoundException("SUBJECT NOT FOUND EXCEPTION");
        }
        Subject subject = optionalSubject.get();
        if (!centerEntityService.isStatusSuperEntity(subject)){
            throw new ResourceNotFoundException("SUBJECT NOT FOUND EXCEPTION");
        }
        return subject;
    }
}
