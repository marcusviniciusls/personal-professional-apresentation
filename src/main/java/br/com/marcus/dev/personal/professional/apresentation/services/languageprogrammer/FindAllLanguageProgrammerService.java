package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class FindAllLanguageProgrammerService {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;

    public Page<LanguageProgrammerResponse> findAll(Pageable pageable){
        Page<LanguageProgrammer> pageLanguageProgrammer = languageProgrammerRepository.findAll(pageable);
        Page<LanguageProgrammerResponse> pageLanguageProgrammerResponse =
                pageLanguageProgrammer.map(x -> languageProgrammerFactory.convertEntityInResponse(x));
        return pageLanguageProgrammerResponse;
    }
}
