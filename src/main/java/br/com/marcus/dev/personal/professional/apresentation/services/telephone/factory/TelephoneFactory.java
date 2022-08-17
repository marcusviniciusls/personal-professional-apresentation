package br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelephoneFactory {

    @Autowired private CenterEntityService centerEntityService;
    @Autowired private TelephoneRepository telephoneRepository;

    public TelephoneDto convertEntityInDto(Telephone telephone){
        TelephoneDto telephoneDto = new TelephoneDto();
        telephoneDto.setDdd(telephone.getDdd());
        telephoneDto.setDdi(telephone.getDdi());
        telephoneDto.setNumber(telephone.getNumber());
        return telephoneDto;
    }

    public List<Telephone> convertDtoInEntityList(List<TelephoneForm> listTelephoneForm, DataPersonal dataPersonal){
        List<Telephone> listTelephone = new ArrayList<>();
        for (TelephoneForm telephoneForm : listTelephoneForm){
            Telephone telephone = new Telephone();
            telephone.setDdd(telephoneForm.getDdd());
            telephone.setDdi(telephoneForm.getDdi());
            telephone.setNumber(telephoneForm.getNumber());
            telephone = (Telephone) centerEntityService.setDataToSave(telephone);
            telephone.setDataPersonal(dataPersonal);
            telephone = telephoneRepository.save(telephone);
            listTelephone.add(telephone);
        }
        return listTelephone;
    }
}
