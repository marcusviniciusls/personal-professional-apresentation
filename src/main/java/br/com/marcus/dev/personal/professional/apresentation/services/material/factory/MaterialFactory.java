package br.com.marcus.dev.personal.professional.apresentation.services.material.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.part.factory.PartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MaterialFactory {

    @Autowired private PartFactory partFactory;
    @Autowired private PartRepository partRepository;

    public MaterialResponse convertEntityInResponse(Material material){
        MaterialResponse materialResponse = new MaterialResponse();
        materialResponse.setName(material.getName());
        PartResponse partResponse = partFactory.convertEntityInResponse(material.getPart());
        materialResponse.setPartResponse(partResponse);
        return materialResponse;
    }

    public Material convertSaveFormInEntity(MaterialSaveForm materialSaveForm){
        Material material = new Material();
        material.setName(materialSaveForm.getName());
        return material;
    }

    public Material convertUpdateFormInEntity(Material material, MaterialUpdateForm materialUpdateForm){
        if (materialUpdateForm.getName() != null && !materialUpdateForm.getName().equals("")){
            material.setName(materialUpdateForm.getName());
        }
        if (materialUpdateForm.getPartId() != null){
            Optional<Part> optionalPart = partRepository.findById(materialUpdateForm.getPartId());
            if (!material.getPart().getId().equals(materialUpdateForm.getPartId()) && optionalPart.isPresent()){
                material.setPart(optionalPart.get());
            }
        }

        return material;
    }
}
