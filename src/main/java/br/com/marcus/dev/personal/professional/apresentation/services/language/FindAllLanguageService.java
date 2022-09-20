package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.language.factory.LanguageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllLanguageService {

    @Autowired private LanguageRepository languageRepository;
    @Autowired private LanguageFactory languageFactory;

    public Page<LanguageResponse> findAll(Pageable pageable){
        Page<Language> pageLanguage = languageRepository.findAll(pageable);
        Page<LanguageResponse> pageLanguageResponse = pageLanguage.map(l -> languageFactory.convertEntityInResponse(l));
        return pageLanguageResponse;
    }
}
