package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory.TelephoneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTelephoneService {

    @Autowired private FindByIdTelephoneService findByIdTelephoneService;
    @Autowired private TelephoneFactory telephoneFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private TelephoneRepository telephoneRepository;

    public TelephoneDto update(TelephoneFormUpdate telephoneFormUpdate, UUID idTelephone){
        Telephone telephoneBD = findByIdTelephoneService.findByIdTelephone(idTelephone);
        Telephone telephoneNew = telephoneFactory.convertFormInEntityUpdate(telephoneBD, telephoneFormUpdate);
        telephoneNew = (Telephone) centerEntityService.setDataToUpdate(telephoneNew);
        telephoneNew = telephoneRepository.save(telephoneNew);
        TelephoneDto telephoneDto = telephoneFactory.convertEntityInDto(telephoneNew);
        return telephoneDto;
    }
}