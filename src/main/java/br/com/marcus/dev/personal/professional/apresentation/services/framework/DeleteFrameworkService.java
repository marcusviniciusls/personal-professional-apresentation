package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteFrameworkService {

    @Autowired private FindByIdFrameworkService findByIdFrameworkService;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Framework framework = findByIdFrameworkService.findByIdEntity(id);
        try{
            frameworkRepository.delete(framework);
        } catch(DataIntegrityViolationException ex){
            framework = (Framework) centerEntityService.setDataToDelete(framework);
            frameworkRepository.save(framework);
        }
    }
}
