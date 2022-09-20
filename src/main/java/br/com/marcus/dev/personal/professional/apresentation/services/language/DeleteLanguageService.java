package br.com.marcus.dev.personal.professional.apresentation.services.language;

import br.com.marcus.dev.personal.professional.apresentation.entities.Language;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteLanguageService {

    @Autowired private FindByIdLanguageService findByIdLanguageService;
    @Autowired private LanguageRepository languageRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Language language = findByIdLanguageService.findByIdEntity(id);
        try{
            languageRepository.delete(language);
        } catch(DataIntegrityViolationException ex){
            language = (Language) centerEntityService.setDataToDelete(language);
            languageRepository.save(language);
        }
    }
}
