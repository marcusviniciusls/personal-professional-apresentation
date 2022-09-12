package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.factory.OfficeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateOfficeService {

    @Autowired private FindByIdOfficeService findByIdOfficeService;
    @Autowired private OfficeFactory officeFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private OfficeRepository officeRepository;

    public OfficeResponse update(UUID id, OfficeUpdateForm officeUpdateForm){
        Office office = findByIdOfficeService.findByIdEntity(id);
        office = officeFactory.convertUpdateFormInEntity(officeUpdateForm, office);
        office = (Office) centerEntityService.setDataToUpdate(office);
        office = officeRepository.save(office);
        return officeFactory.convertEntityInResponse(office);
    }
}
