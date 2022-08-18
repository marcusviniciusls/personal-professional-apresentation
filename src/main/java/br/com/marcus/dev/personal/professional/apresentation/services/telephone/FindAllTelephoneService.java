package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory.TelephoneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllTelephoneService {

    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private TelephoneFactory telephoneFactory;

    public Page<TelephoneDto> findAll(Pageable pageable){
        Page<Telephone> pageTelephone = telephoneRepository.findAll(pageable);
        Page<TelephoneDto> pageTelephoneDto = pageTelephone.map(x -> telephoneFactory.convertEntityInDto(x));
        return pageTelephoneDto;
    }
}
