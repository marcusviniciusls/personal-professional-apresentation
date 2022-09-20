package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.language.factory.LanguageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateLanguageService {

    @Autowired private LanguageFactory languageFactory;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private FindByIdLanguageService findByIdLanguageService;
    @Autowired private CenterEntityService centerEntityService;

    public void update(UUID id, LanguageUpdateForm languageUpdateForm){
        Language language = findByIdLanguageService.findByIdEntity(id);
        language = languageFactory.convertUpdateFormInEntity(language, languageUpdateForm);
        language = (Language) centerEntityService.setDataToUpdate(language);
        languageRepository.save(language);
    }
}
