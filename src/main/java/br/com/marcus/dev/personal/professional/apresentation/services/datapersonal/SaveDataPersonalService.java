package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorSavingRecordException;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory.DataPersonalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaveDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private DataPersonalFactory dataPersonalFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;

    public DataPersonalDto save(DataPersonalFullForm dataPersonalFullForm){
        try {
            DataPersonal dataPersonal = dataPersonalFactory.convertDtoInEntity(dataPersonalFullForm);
            DataPersonalDto dataPersonalDto = dataPersonalFactory.convertEntityInDto(dataPersonal);
            return dataPersonalDto;
        } catch (NumberFormatException numberFormatException){
            throw new ErrorSavingRecordException("Error Saving Record - DataPersonal " +numberFormatException.getMessage());
        }
    }
}
