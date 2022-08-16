package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.email.factory.EmailFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.factory.TelephoneFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private EmailFactory emailFactory;
    @Autowired private TelephoneFactory telephoneFactory;

    public DataPersonalDto save(DataPersonalFullForm dataPersonalFullForm){


    }
}
