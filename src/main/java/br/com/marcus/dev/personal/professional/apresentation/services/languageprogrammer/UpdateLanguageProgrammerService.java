package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerUpdateFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateLanguageProgrammerService {

    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    public LanguageProgrammerResponse update(UUID id, LanguageProgrammerUpdateFrom request){
        LanguageProgrammer languageProgrammer = findByIdLanguageProgrammerService.findByIdEntity(id);
        languageProgrammer = languageProgrammerFactory.convertUpdateFormInEntity(languageProgrammer, request);
        languageProgrammer = (LanguageProgrammer) centerEntityService.setDataToUpdate(languageProgrammer);
        languageProgrammer = languageProgrammerRepository.save(languageProgrammer);
        LanguageProgrammerResponse response = languageProgrammerFactory.convertEntityInResponse(languageProgrammer);
        return response;
    }
}
