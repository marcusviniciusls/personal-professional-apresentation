package br.com.marcus.dev.personal.professional.apresentation.services.email.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import org.springframework.stereotype.Component;

@Component
public class EmailFactory {

    public EmailDto convertEntityInDto(Email email){
        EmailDto emailDto = new EmailDto();
        emailDto.setEmail(email.getEmail());
        return emailDto;
    }

    public Email convertDtoInEntity(EmailForm emailForm){
        Email email = new Email();
        email.setEmail(emailForm.getEmail());
        return email;
    }
}
