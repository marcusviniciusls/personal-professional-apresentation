package br.com.marcus.dev.personal.professional.apresentation.services.material;

import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import br.com.marcus.dev.personal.professional.apresentation.repository.MaterialRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteMaterialService {

    @Autowired private FindByIdMaterialService findByIdMaterialService;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Material material = findByIdMaterialService.findByIdEntity(id);
        try{
            materialRepository.delete(material);
        } catch(DataIntegrityViolationException ex){
            material = (Material) centerEntityService.setDataToDelete(material);
            materialRepository.save(material);
        }
    }
}