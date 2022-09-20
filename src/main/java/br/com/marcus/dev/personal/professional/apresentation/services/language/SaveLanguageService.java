package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.language.factory.LanguageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveLanguageService {

    @Autowired private LanguageFactory languageFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private PartRepository partRepository;

    public LanguageResponse save(LanguageSaveForm languageSaveForm){
        Language language = languageFactory.convertSaveFormInEntity(languageSaveForm);
        language = (Language) centerEntityService.setDataToSave(language);
        language = languageRepository.save(language);

        return languageFactory.convertEntityInResponse(language);
    }
}
