package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID uuid){
        try {
            Optional<DataPersonal> optionalDataPersonal = dataPersonalRepository.findById(uuid);
            if (optionalDataPersonal.isEmpty()){
                throw new ResourceNotFoundException("ID Not Found Exception");
            }
            if (!centerEntityService.isStatusSuperEntity(optionalDataPersonal.get())){
                throw new ResourceNotFoundException("ID Not Found Exception");
            }
            dataPersonalRepository.deleteById(uuid);
        } catch (DataIntegrityViolationException ex){
            Optional<DataPersonal> optionalBranchActivity = dataPersonalRepository.findById(uuid);
            DataPersonal dataPersonalUpdate = (DataPersonal) centerEntityService.setDataToDelete(optionalBranchActivity.get());
            dataPersonalRepository.save(dataPersonalUpdate);
        }
    }
}
