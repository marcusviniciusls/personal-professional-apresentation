package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

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
public class FindByIdDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DataPersonalFactory dataPersonalFactory;

    public DataPersonalDto findById(UUID id){
        Optional<DataPersonal> optinalDataPersonal = dataPersonalRepository.findById(id);
        if (optinalDataPersonal.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        DataPersonal dataPersonal = optinalDataPersonal.get();
        if (!centerEntityService.isStatusSuperEntity(dataPersonal)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        DataPersonalDto dataPersonalDto = dataPersonalFactory.convertEntityInDto(dataPersonal);
        return dataPersonalDto;
    }

    public DataPersonal findByIdDataPersonal(UUID id){
        Optional<DataPersonal> optinalDataPersonal = dataPersonalRepository.findById(id);
        if (optinalDataPersonal.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        DataPersonal dataPersonal = optinalDataPersonal.get();
        if (!centerEntityService.isStatusSuperEntity(dataPersonal)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        return dataPersonal;
    }
}
