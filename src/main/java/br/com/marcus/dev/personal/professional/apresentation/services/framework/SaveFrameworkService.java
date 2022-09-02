package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.factory.FrameworkFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveFrameworkService {

    @Autowired private FrameworkFactory frameworkFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FrameworkRepository frameworkRepository;

    public FrameworkResponse save(FrameworkSaveForm frameworkSaveForm){
        Framework framework = frameworkFactory.convertFormSaveInEntity(frameworkSaveForm);
        framework = (Framework) centerEntityService.setDataToSave(framework);
        framework = frameworkRepository.save(framework);
        FrameworkResponse frameworkResponse = frameworkFactory.convertEntityInResponse(framework);
        return frameworkResponse;
    }
}
