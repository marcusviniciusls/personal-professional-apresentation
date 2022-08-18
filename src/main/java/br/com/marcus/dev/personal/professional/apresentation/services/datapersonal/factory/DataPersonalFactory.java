package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory.TelephoneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Component
public class DataPersonalFactory {

    @Autowired private EmailFactory emailFactory;
    @Autowired private TelephoneFactory telephoneFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DataPersonalRepository dataPersonalRepository;

    public DataPersonalDto convertEntityInDto(DataPersonal dataPersonal){
        DataPersonalDto dataPersonalDto = new DataPersonalDto();
        dataPersonalDto.setFullname(dataPersonal.getFullName());
        dataPersonalDto.setAge(dataPersonal.getAge());
        dataPersonalDto.setMartialStatus(dataPersonal.getMaritalStatus().getNumber());
        dataPersonalDto.setBirthDate(dataPersonal.getBirthDate());

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

    public DataPersonal convertDtoInEntitySave(DataPersonalFullForm dataPersonalFullForm){
        DataPersonal dataPersonal = new DataPersonal();
        dataPersonal.setFullName(dataPersonalFullForm.getFullName());
        int year = returnYear(dataPersonalFullForm.getBirthDate());
        int month = returnMonth(dataPersonalFullForm.getBirthDate());
        int day = returnDay(dataPersonalFullForm.getBirthDate());
        LocalDate dateBirthday = LocalDate.of(year, month, day);
        dataPersonal.setBirthDate(dateBirthday);
        Period period = Period.between(dateBirthday, LocalDate.now());
        dataPersonal.setAge(period.getYears());
        dataPersonal.setMaritalStatus(MaritalStatus.toEnum(dataPersonalFullForm.getMaritalStatus()));
        dataPersonal = (DataPersonal) centerEntityService.setDataToSave(dataPersonal);
        dataPersonal = dataPersonalRepository.save(dataPersonal);
        List<Telephone> listTelephone = telephoneFactory.convertDtoInEntityListSave(dataPersonalFullForm.getListTelephoneForm(), dataPersonal);
        dataPersonal.setListTelephone(listTelephone);
        List<Email> listEmail = emailFactory.convertDtoInEntityListSave(dataPersonalFullForm.getListEmailForm(), dataPersonal);
        dataPersonal.setListEmail(listEmail);
        return dataPersonal;
    }

    private int returnDay(String date){
        return Integer.parseInt(date.substring(0,2));
    }

    private int returnMonth(String date){
        return Integer.parseInt(date.substring(3,5));
    }

    private int returnYear(String date){
        return Integer.parseInt(date.substring(6));
    }

    public DataPersonal convertDtoInEntityUpdate(DataPersonalFullFormUpdate dataPersonalFullForm, DataPersonal dataPersonal){
        dataPersonal.setFullName(dataPersonalFullForm.getFullName());
        int year = returnYear(dataPersonalFullForm.getBirthDate());
        int month = returnMonth(dataPersonalFullForm.getBirthDate());
        int day = returnDay(dataPersonalFullForm.getBirthDate());
        LocalDate dateBirthday = LocalDate.of(year, month, day);
        dataPersonal.setBirthDate(dateBirthday);
        Period period = Period.between(dateBirthday, LocalDate.now());
        dataPersonal.setAge(period.getYears());
        dataPersonal.setMaritalStatus(MaritalStatus.toEnum(dataPersonalFullForm.getMaritalStatus()));
        dataPersonal = (DataPersonal) centerEntityService.setDataToSave(dataPersonal);
        return dataPersonal;
    }
}
