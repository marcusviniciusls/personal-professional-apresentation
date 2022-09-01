package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.GraduationResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdGraduationService {

    @Autowired private GraduationRepository graduationRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private GraduationFactory graduationFactory;

    public GraduationResponse findById(UUID id){
        Optional<Graduation> optionalGraduation = graduationRepository.findById(id);
        if (optionalGraduation.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Graduation graduation = optionalGraduation.get();
        if (!centerEntityService.isStatusSuperEntity(graduation)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        GraduationResponse graduationResponse = graduationFactory.convertEntityInDto(graduation);
        return graduationResponse;
    }

    public Graduation findByIdEntity(UUID id){
        Optional<Graduation> optionalGraduation = graduationRepository.findById(id);
        if (optionalGraduation.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Graduation graduation = optionalGraduation.get();
        if (!centerEntityService.isStatusSuperEntity(graduation)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        return graduation;
    }
}
