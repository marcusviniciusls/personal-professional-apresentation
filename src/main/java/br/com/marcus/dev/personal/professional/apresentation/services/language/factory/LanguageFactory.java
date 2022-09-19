package br.com.marcus.dev.personal.professional.apresentation.services.language.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageFactory {

    @Autowired private ModelMapper modelMapper;

    public LanguageResponse convertEntityInResponse(Language language){
        LanguageResponse languageResponse = new LanguageResponse();
        languageResponse.setName(language.getName());
        languageResponse.setLevel(language.getLevel());
        return languageResponse;
    }
}
