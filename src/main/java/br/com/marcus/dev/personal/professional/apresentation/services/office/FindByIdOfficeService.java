package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.factory.OfficeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdOfficeService {

    @Autowired private OfficeRepository officeRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private OfficeFactory officeFactory;

    public OfficeResponse findById(UUID id){
        Optional<Office> optionalOffice = officeRepository.findById(id);
        if (optionalOffice.isEmpty()){
            throw new ResourceNotFoundException("Office Not Found Exception");
        }
        Office office = optionalOffice.get();
        if (!centerEntityService.isStatusSuperEntity(office)){
            throw new ResourceNotFoundException("Office Not Found Exception");
        }
        return officeFactory.convertEntityInResponse(office);
    }
}
