package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.material.factory.MaterialFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdMaterialService {

    @Autowired private MaterialRepository materialRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private MaterialFactory materialFactory;

    public MaterialResponse findById(UUID id){
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        if (optionalMaterial.isEmpty()){
            throw new ResourceNotFoundException("Material Not Found Exception");
        }
        Material material = optionalMaterial.get();
        if (!centerEntityService.isStatusSuperEntity(material)){
            throw new ResourceNotFoundException("Material Not Found Exception");
        }
        return materialFactory.convertEntityInResponse(material);
    }

    public Material findByIdEntity(UUID id){
        Optional<Material> optionalMaterial = materialRepository.findById(id);
        if (optionalMaterial.isEmpty()){
            throw new ResourceNotFoundException("Material Not Found Exception");
        }
        Material material = optionalMaterial.get();
        if (!centerEntityService.isStatusSuperEntity(material)){
            throw new ResourceNotFoundException("Material Not Found Exception");
        }
        return material;
    }
}