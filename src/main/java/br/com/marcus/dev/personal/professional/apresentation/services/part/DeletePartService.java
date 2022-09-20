package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePartService {

    @Autowired private FindByIdPartService findByIdPartService;
    @Autowired private PartRepository partRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Part part = findByIdPartService.findByIdEntity(id);
        try{
            partRepository.delete(part);
        } catch(DataIntegrityViolationException ex){
            part = (Part) centerEntityService.setDataToDelete(part);
            partRepository.save(part);
        }
    }
}
