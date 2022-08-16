package br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import org.springframework.stereotype.Component;

@Component
public class TelephoneFactory {

    public TelephoneDto convertEntityInDto(Telephone telephone){
        TelephoneDto telephoneDto = new TelephoneDto();
        telephoneDto.setDdd(telephone.getDdd());
        telephoneDto.setDdi(telephone.getDdi());
        telephoneDto.setNumber(telephone.getNumber());
        return telephoneDto;
    }
}
