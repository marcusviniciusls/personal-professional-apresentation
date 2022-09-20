package br.com.marcus.dev.personal.professional.apresentation.services.language.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
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
        for (Part part : language.getListPart()){
            PartResponse partResponse = new PartResponse();
            partResponse.setName(part.getName());
            partResponse.setLevel(part.getLevel());
            languageResponse.addListPart(partResponse);
        }
        return languageResponse;
    }
}
