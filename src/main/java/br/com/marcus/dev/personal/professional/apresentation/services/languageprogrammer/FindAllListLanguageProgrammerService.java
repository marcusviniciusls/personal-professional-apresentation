package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FindAllListLanguageProgrammerService {

    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;
    @Autowired private CenterEntityService centerEntityService;

    public List<LanguageProgrammer> findAllLanguageProgrammer(List<ListLanguageProgrammer> listIdLanguageProgrammer){
        List<LanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
        for (ListLanguageProgrammer list : listIdLanguageProgrammer){
            Optional<LanguageProgrammer> optionalLanguageProgrammer = languageProgrammerRepository.findById(list.getId());
            if (optionalLanguageProgrammer.isPresent() || centerEntityService.isStatusSuperEntity(optionalLanguageProgrammer.get())){
                LanguageProgrammer languageProgrammer = optionalLanguageProgrammer.get();
                listLanguageProgrammer.add(languageProgrammer);
            }
        }
        return listLanguageProgrammer;
    }
}
