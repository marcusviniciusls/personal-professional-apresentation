package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdEmailService {

    @Autowired private EmailRepository emailRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private EmailFactory emailFactory;

    public EmailDto findById(UUID id){
        Optional<Email> optionalEmail = emailRepository.findById(id);
        if (optionalEmail.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Email email = optionalEmail.get();
        if (!centerEntityService.isStatusSuperEntity(email)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        EmailDto emailDto = emailFactory.convertEntityInDto(email);
        return emailDto;
    }
}
