package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory.TelephoneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataPersonalFactory {

    @Autowired private EmailFactory emailFactory;
    @Autowired private TelephoneFactory telephoneFactory;

    public DataPersonalDto convertEntityInDto(DataPersonal dataPersonal){
        DataPersonalDto dataPersonalDto = new DataPersonalDto();
        dataPersonalDto.setFullname(dataPersonal.getFullName());
        dataPersonalDto.setAge(dataPersonal.getAge());
        dataPersonalDto.setMartialStatus(dataPersonal.getMaritalStatus().getNumber());

        for(Telephone telephone : dataPersonal.getListTelephone()){
            TelephoneDto telephoneDto = telephoneFactory.convertEntityInDto(telephone);
            dataPersonalDto.addListTelephoneDto(telephoneDto);
        }

        for(Email email : dataPersonal.getListEmail()){
            EmailDto emailDto = emailFactory.convertEntityInDto(email);
            dataPersonalDto.addListEmailDto(emailDto);
        }

        return dataPersonalDto;
    }
}
