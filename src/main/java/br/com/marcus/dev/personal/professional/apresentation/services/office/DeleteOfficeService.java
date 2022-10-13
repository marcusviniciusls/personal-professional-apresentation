package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteOfficeService {

    @Autowired private FindByIdOfficeService findByIdOfficeService;
    @Autowired private OfficeRepository officeRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Office office = findByIdOfficeService.findByIdEntity(id);
        try {
            officeRepository.delete(office);
        } catch(DataIntegrityViolationException ex){
            office = (Office) centerEntityService.setDataToDelete(office);
            officeRepository.save(office);
        }
    }
}
