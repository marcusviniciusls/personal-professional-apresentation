package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteTelephoneService {

    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FindByIdTelephoneService findByIdTelephoneService;

    public void delete(UUID id){
        try{
            Optional<Telephone> optionalTelephone = telephoneRepository.findById(id);
            if (optionalTelephone.isEmpty()){
                throw new ResourceNotFoundException("ID Not Found Exception");
            }
            if (!centerEntityService.isStatusSuperEntity(optionalTelephone.get())){
                throw new ResourceNotFoundException("ID Not Found Exception");
            }
            telephoneRepository.deleteById(id);
        } catch(DataIntegrityViolationException ex){
            Optional<Telephone> optionalTelephone = telephoneRepository.findById(id);
            Telephone telephone = optionalTelephone.get();
            telephone = (Telephone) centerEntityService.setDataToDelete(telephone);
            telephoneRepository.save(telephone);
        }
    }
}
