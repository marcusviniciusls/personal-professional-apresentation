package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.material.factory.MaterialFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateMaterialService {

    @Autowired private FindByIdMaterialService findByIdMaterialService;
    @Autowired private MaterialFactory materialFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private MaterialRepository materialRepository;

    public void update(UUID id, MaterialUpdateForm materialUpdateForm){
        Material material = findByIdMaterialService.findByIdEntity(id);
        material = materialFactory.convertUpdateFormInEntity(material, materialUpdateForm);
        material = (Material) centerEntityService.setDataToUpdate(material);
        materialRepository.save(material);
    }
}
