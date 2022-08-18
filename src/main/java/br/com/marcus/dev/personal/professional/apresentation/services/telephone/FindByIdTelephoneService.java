package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Email;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory.TelephoneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdTelephoneService {

    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private TelephoneFactory telephoneFactory;

    public TelephoneDto findById(UUID id){
        Optional<Telephone> optionalTelephone = telephoneRepository.findById(id);
        if (optionalTelephone.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Telephone telephone = optionalTelephone.get();
        if (!centerEntityService.isStatusSuperEntity(telephone)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        TelephoneDto telephoneDto = telephoneFactory.convertEntityInDto(telephone);
        return telephoneDto;
    }

    public Telephone findByIdTelephone(UUID id){
        Optional<Telephone> optionalTelephone = telephoneRepository.findById(id);
        if (optionalTelephone.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Telephone telephone = optionalTelephone.get();
        if (!centerEntityService.isStatusSuperEntity(telephone)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        return telephone;
    }
}