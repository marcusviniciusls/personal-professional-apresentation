package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteEmailService {

    @Autowired private EmailRepository emailRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FindByIdEmailService findByIdEmailService;

    public void delete(UUID id){
        try{
            findByIdEmailService.findById(id);
            emailRepository.deleteById(id);
        } catch(DataIntegrityViolationException ex){
            Optional<Email> optionalEmail = emailRepository.findById(id);
            Email email = optionalEmail.get();
            email = (Email) centerEntityService.setDataToDelete(email);
            emailRepository.save(email);
        }
    }
}
