package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.material.factory.MaterialFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.part.FindByIdPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMaterialService {

    @Autowired private FindByIdPartService findByIdPartService;
    @Autowired private MaterialFactory materialFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private MaterialRepository materialRepository;

    public MaterialResponse save(MaterialSaveForm materialSaveForm){
        Part part = findByIdPartService.findByIdEntity(materialSaveForm.getPartId());
        Material material = materialFactory.convertSaveFormInEntity(materialSaveForm);
        material.setPart(part);
        material = (Material) centerEntityService.setDataToSave(material);
        material = materialRepository.save(material);
        return materialFactory.convertEntityInResponse(material);
    }
}
