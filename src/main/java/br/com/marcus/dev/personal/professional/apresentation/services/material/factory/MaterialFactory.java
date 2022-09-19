package br.com.marcus.dev.personal.professional.apresentation.services.material.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.services.part.factory.PartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaterialFactory {

    @Autowired private PartFactory partFactory;

    public MaterialResponse convertEntityInResponse(Material material){
        MaterialResponse materialResponse = new MaterialResponse();
        materialResponse.setName(material.getName());
        PartResponse partResponse = partFactory.convertEntityInResponse(material.getPart());
        materialResponse.setPartResponse(partResponse);
        return materialResponse;
    }
}
