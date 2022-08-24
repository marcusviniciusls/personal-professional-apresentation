package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateEmailService {

    @Autowired private FindByIdEmailService findByIdEmailService;
    @Autowired private EmailFactory emailFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private EmailRepository emailRepository;

    public EmailDto update(EmailFormUpdate emailFormUpdate, UUID id){
        Email email = findByIdEmailService.findByIdEmail(id);
        email = emailFactory.convertFormInEntityUpdate(emailFormUpdate.getEmail(), email);
        email = (Email) centerEntityService.setDataToUpdate(email);
        email = emailRepository.save(email);
        EmailDto emailDto = emailFactory.convertEntityInDto(email);
        return emailDto;
    }
}
