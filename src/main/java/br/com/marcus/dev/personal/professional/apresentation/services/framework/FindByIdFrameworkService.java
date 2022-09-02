package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.factory.FrameworkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdFrameworkService {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FrameworkFactory frameworkFactory;

    public FrameworkResponse findById(UUID id){
        Optional<Framework> optionalFramework = frameworkRepository.findById(id);
        if (optionalFramework.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Framework framework = optionalFramework.get();
        if (!centerEntityService.isStatusSuperEntity(framework)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        FrameworkResponse frameworkResponse = frameworkFactory.convertEntityInDto(framework);
        return frameworkResponse;
    }

    public Framework findByIdEntity(UUID id){
        Optional<Framework> optionalFramework = frameworkRepository.findById(id);
        if (optionalFramework.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Framework framework = optionalFramework.get();
        if (!centerEntityService.isStatusSuperEntity(framework)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        return framework;
    }
}
