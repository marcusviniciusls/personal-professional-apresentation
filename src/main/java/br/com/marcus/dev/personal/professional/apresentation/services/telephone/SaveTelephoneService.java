package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.FindByIdDataPersonalService;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory.TelephoneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaveTelephoneService {

    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private FindByIdDataPersonalService findByIdDataPersonalService;
    @Autowired private TelephoneFactory telephoneFactory;
    @Autowired private CenterEntityService centerEntityService;

    public TelephoneDto save(TelephoneFormSave telephoneFormSave){
        DataPersonal dataPersonal = findByIdDataPersonalService.findByIdDataPersonal(UUID.fromString(telephoneFormSave.getId()));
        Telephone telephone = telephoneFactory.convertFormInEntitySave(telephoneFormSave);
        telephone.setDataPersonal(dataPersonal);
        telephone = (Telephone) centerEntityService.setDataToSave(telephone);
        telephone = telephoneRepository.save(telephone);
        TelephoneDto telephoneDto = telephoneFactory.convertEntityInDto(telephone);
        return telephoneDto;
    }
}