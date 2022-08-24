package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorSavingRecordException;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.EmailRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory.DataPersonalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private DataPersonalFactory dataPersonalFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private EmailRepository emailRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private CheckSaveDataPersonalService checkSaveDataPersonalService;

    public DataPersonalDto save(DataPersonalFullForm dataPersonalFullForm){
        try {
            if (checkSaveDataPersonalService.verifyCheckSaveDataPersonal()){
                DataPersonal dataPersonal = dataPersonalFactory.convertDtoInEntitySave(dataPersonalFullForm);
                DataPersonalDto dataPersonalDto = dataPersonalFactory.convertEntityInDto(dataPersonal);
                return dataPersonalDto;
            } else {
                throw new ErrorSavingRecordException("Erro Saving Record - Data Already Registered");
            }
        } catch (NumberFormatException numberFormatException){
            throw new ErrorSavingRecordException("Error Saving Record - DataPersonal " +numberFormatException.getMessage());
        }
    }
}
