package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.part.factory.PartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePartService {

    @Autowired private FindByIdPartService findByIdPartService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private PartRepository partRepository;
    @Autowired private PartFactory partFactory;

    public void update(UUID id, PartUpdateForm partUpdateForm){
        Part part = findByIdPartService.findByIdEntity(id);
        part = partFactory.convertUpdateFormInEntity(part, partUpdateForm);
        part = (Part) centerEntityService.setDataToUpdate(part);
        partRepository.save(part);
    }
}
