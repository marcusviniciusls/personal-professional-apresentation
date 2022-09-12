package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.factory.OfficeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveOfficeService {

    @Autowired private OfficeFactory officeFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private OfficeRepository officeRepository;

    public OfficeResponse save(OfficeSaveForm officeSaveForm){
        Office office = officeFactory.convertSaveFormInEntity(officeSaveForm);
        office = (Office) centerEntityService.setDataToSave(office);
        office = officeRepository.save(office);
        return officeFactory.convertEntityInResponse(office);
    }
}
