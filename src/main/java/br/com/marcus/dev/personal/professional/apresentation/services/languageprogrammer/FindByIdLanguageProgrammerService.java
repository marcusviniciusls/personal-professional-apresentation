package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdLanguageProgrammerService {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;

    public LanguageProgrammerResponse findById(UUID id){
        Optional<LanguageProgrammer> optionalLanguageProgrammer = languageProgrammerRepository.findById(id);
        if (optionalLanguageProgrammer.isEmpty()){
            throw new ResourceNotFoundException("Language Programmer Not Found Exception");
        }
        LanguageProgrammer languageProgrammer = optionalLanguageProgrammer.get();
        if (!centerEntityService.isStatusSuperEntity(languageProgrammer)){
            throw new ResourceNotFoundException("Language Programmer Not Found Exception");
        }
        LanguageProgrammerResponse languageProgrammerResponse = languageProgrammerFactory.convertEntityInResponse(languageProgrammer);
        return languageProgrammerResponse;
    }

    public LanguageProgrammer findByIdEntity(UUID id){
        Optional<LanguageProgrammer> optionalLanguageProgrammer = languageProgrammerRepository.findById(id);
        if (optionalLanguageProgrammer.isEmpty()){
            throw new ResourceNotFoundException("Language Programmer Not Found Exception");
        }
        LanguageProgrammer languageProgrammer = optionalLanguageProgrammer.get();
        if (!centerEntityService.isStatusSuperEntity(languageProgrammer)){
            throw new ResourceNotFoundException("Language Programmer Not Found Exception");
        }
        return languageProgrammer;
    }
}
