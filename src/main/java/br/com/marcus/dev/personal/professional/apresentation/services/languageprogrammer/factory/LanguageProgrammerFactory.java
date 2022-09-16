package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerSaveFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerUpdateFrom;
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

    public LanguageProgrammer convertUpdateSaveInEntity(LanguageProgrammerSaveFrom languageProgrammerSaveFrom){
        return modelMapper.map(languageProgrammerSaveFrom, LanguageProgrammer.class);
    }

    public LanguageProgrammer convertUpdateFormInEntity(LanguageProgrammer languageProgrammer, LanguageProgrammerUpdateFrom languageProgrammerUpdateFrom){
        if (languageProgrammerUpdateFrom.getName() != null && !languageProgrammerUpdateFrom.getName().equals("")){
            languageProgrammer.setName(languageProgrammerUpdateFrom.getName());
        }
        if (languageProgrammerUpdateFrom.getDescription() != null && !languageProgrammerUpdateFrom.getDescription().equals("")){
            languageProgrammer.setDescription(languageProgrammerUpdateFrom.getDescription());
        }
        return languageProgrammer;
    }
}
