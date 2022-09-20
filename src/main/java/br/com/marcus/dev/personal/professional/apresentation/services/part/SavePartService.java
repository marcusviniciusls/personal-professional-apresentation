package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.part.factory.PartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavePartService {

    @Autowired private PartFactory partFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private PartRepository partRepository;

    public PartResponse save(PartSaveForm partSaveForm){
        Part part = partFactory.convertSaveFormInEntity(partSaveForm);
        part = (Part) centerEntityService.setDataToSave(part);
        part = partRepository.save(part);
        return partFactory.convertEntityInResponse(part);
    }
}