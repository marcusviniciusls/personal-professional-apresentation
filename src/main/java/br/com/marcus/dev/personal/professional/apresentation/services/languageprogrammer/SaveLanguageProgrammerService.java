package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerSaveFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveLanguageProgrammerService {

    @Autowired
    private LanguageProgrammerFactory languageProgrammerFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    public LanguageProgrammerResponse save(LanguageProgrammerSaveFrom languageProgrammerSaveFrom){
        LanguageProgrammer languageProgrammer = languageProgrammerFactory.convertUpdateSaveInEntity(languageProgrammerSaveFrom);
        languageProgrammer = (LanguageProgrammer) centerEntityService.setDataToSave(languageProgrammer);
        languageProgrammer = languageProgrammerRepository.save(languageProgrammer);
        LanguageProgrammerResponse languageProgrammerResponse = languageProgrammerFactory.convertEntityInResponse(languageProgrammer);
        return languageProgrammerResponse;
    }
}
