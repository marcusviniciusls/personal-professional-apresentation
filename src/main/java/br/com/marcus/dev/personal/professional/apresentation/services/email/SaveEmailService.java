package br.com.marcus.dev.personal.professional.apresentation.services.email;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.FindByIdDataPersonalService;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaveEmailService {

    @Autowired private EmailRepository emailRepository;
    @Autowired private FindByIdDataPersonalService findByIdDataPersonalService;
    @Autowired private EmailFactory emailFactory;
    @Autowired private CenterEntityService centerEntityService;

    public EmailDto save(EmailFormSave emailFormSave){
        DataPersonal dataPersonal = findByIdDataPersonalService.findByIdDataPersonal(UUID.fromString(emailFormSave.getId()));
        Email email = emailFactory.convertFormInEntity(emailFormSave.getEmail());
        email.setDataPersonal(dataPersonal);
        email = (Email) centerEntityService.setDataToSave(email);
        email = emailRepository.save(email);
        EmailDto emailDto = emailFactory.convertEntityInDto(email);
        return emailDto;
    }
}
