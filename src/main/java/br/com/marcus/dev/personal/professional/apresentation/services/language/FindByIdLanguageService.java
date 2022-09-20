package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdLanguageService {

    @Autowired private LanguageRepository languageRepository;
    @Autowired private CenterEntityService centerEntityService;

    public Language findByIdEntity(UUID id){
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isEmpty()){
            throw new ResourceNotFoundException("Language Not Found Exception");
        }
        Language language = optionalLanguage.get();
        if (!centerEntityService.isStatusSuperEntity(language)){
            throw new ResourceNotFoundException("Language Not Found Exception");
        }
        return language;
    }
}
