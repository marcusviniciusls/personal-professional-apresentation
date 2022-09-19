package br.com.marcus.dev.personal.professional.apresentation.services.part.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.services.language.factory.LanguageFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartFactory {

    @Autowired private LanguageFactory languageFactory;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;

    public PartResponse convertEntityInResponse(Part part){
        PartResponse partResponse = new PartResponse();
        LanguageResponse languageResponse = languageFactory.convertEntityInResponse(part.getLanguage());

        partResponse.setName(part.getName());
        partResponse.setLevel(part.getLevel());
        partResponse.setLanguageResponse(languageResponse);

        return partResponse;
    }
}
