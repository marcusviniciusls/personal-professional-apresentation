package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.factory.FrameworkFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateFrameworkService {

    @Autowired private FrameworkFactory frameworkFactory;
    @Autowired private FindByIdFrameworkService findByIdFrameworkService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FrameworkRepository frameworkRepository;

    public FrameworkResponse update(FrameworkUpdateForm frameworkUpdateForm, UUID id){
        Framework framework = findByIdFrameworkService.findByIdEntity(id);
        framework = frameworkFactory.convertUpdateFormInEntity(frameworkUpdateForm, framework);
        framework = (Framework) centerEntityService.setDataToUpdate(framework);
        framework = frameworkRepository.save(framework);
        FrameworkResponse frameworkResponse = frameworkFactory.convertEntityInResponse(framework);
        return frameworkResponse;
    }
}
