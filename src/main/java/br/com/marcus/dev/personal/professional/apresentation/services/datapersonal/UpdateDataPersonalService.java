package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory.DataPersonalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DataPersonalFactory dataPersonalFactory;

    public DataPersonalDto update(DataPersonalFullFormUpdate update, UUID id){
        Optional<DataPersonal> optionalDataPersonal = dataPersonalRepository.findById(id);
        if (optionalDataPersonal.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        DataPersonal dataPersonal = optionalDataPersonal.get();
        if (!centerEntityService.isStatusSuperEntity(dataPersonal)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        dataPersonal = dataPersonalFactory.convertDtoInEntityUpdate(update, dataPersonal);
        dataPersonal = (DataPersonal) centerEntityService.setDataToUpdate(dataPersonal);
        dataPersonal = dataPersonalRepository.save(dataPersonal);
        return dataPersonalFactory.convertEntityInDto(dataPersonal);
    }
}
