package br.com.marcus.dev.personal.professional.apresentation.services.email.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailFactory {

    @Autowired private CenterEntityService centerEntityService;
    @Autowired private EmailRepository emailRepository;

    public EmailDto convertEntityInDto(Email email){
        EmailDto emailDto = new EmailDto();
        emailDto.setEmail(email.getEmail());
        return emailDto;
    }

    public Email convertFormInEntity(String emailForm){
        Email email = new Email();
        email.setEmail(emailForm);
        return email;
    }

    public List<Email> convertDtoInEntityListSave(List<EmailForm> listEmailForm, DataPersonal dataPersonal){
        List<Email> listEmail = new ArrayList<>();
        for (EmailForm emailForm : listEmailForm){
            Email email = new Email();
            email.setEmail(emailForm.getEmail());
            email.setDataPersonal(dataPersonal);
            email = (Email) centerEntityService.setDataToSave(email);
            email = emailRepository.save(email);
            listEmail.add(email);
        }
        return listEmail;
    }
}
