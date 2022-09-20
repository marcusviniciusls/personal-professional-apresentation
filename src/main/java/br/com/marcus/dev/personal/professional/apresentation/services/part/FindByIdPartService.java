package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.part.factory.PartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdPartService {

    @Autowired private PartRepository partRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private PartFactory partFactory;

    public PartResponse findById(UUID id){
        Optional<Part> optionalPart = partRepository.findById(id);
        if (optionalPart.isEmpty()){
            throw new ResourceNotFoundException("Part Not Found Exception");
        }
        Part part = optionalPart.get();
        if (!centerEntityService.isStatusSuperEntity(part)){
            throw new ResourceNotFoundException("Part Not Found Exception");
        }
        return partFactory.convertEntityInResponse(part);
    }

    public Part findByIdEntity(UUID id){
        Optional<Part> optionalPart = partRepository.findById(id);
        if (optionalPart.isEmpty()){
            throw new ResourceNotFoundException("Part Not Found Exception");
        }
        Part part = optionalPart.get();
        if (!centerEntityService.isStatusSuperEntity(part)){
            throw new ResourceNotFoundException("Part Not Found Exception");
        }
        return part;
    }
}
