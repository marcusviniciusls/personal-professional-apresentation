package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageProgrammerFactory {

    @Autowired private ModelMapper modelMapper;

    public LanguageProgrammerResponse convertEntityInResponse(LanguageProgrammer languageProgrammer){
        return modelMapper.map(languageProgrammer, LanguageProgrammerResponse.class);
    }
}
