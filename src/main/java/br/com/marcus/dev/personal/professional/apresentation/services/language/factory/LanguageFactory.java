package br.com.marcus.dev.personal.professional.apresentation.services.language.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListPart;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LanguageFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private PartRepository partRepository;
    @Autowired private CenterEntityService centerEntityService;

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

    public Language convertSaveFormInEntity(LanguageSaveForm languageSaveForm){
        return new Language(languageSaveForm.getName(), Level.toEnum(languageSaveForm.getLevel()));
    }
}