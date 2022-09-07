package br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer;

import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DeleteLanguageProgrammerService {

    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

    public void delete(UUID id){
        LanguageProgrammer languageProgrammer = findByIdLanguageProgrammerService.findByIdEntity(id);
        try{
            languageProgrammerRepository.deleteById(id);
        } catch(DataIntegrityViolationException ex){
            languageProgrammer = (LanguageProgrammer) centerEntityService.setDataToDelete(languageProgrammer);
            languageProgrammerRepository.save(languageProgrammer);
        }
    }
}
