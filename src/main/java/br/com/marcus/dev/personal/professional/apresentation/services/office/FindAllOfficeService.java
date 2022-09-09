package br.com.marcus.dev.personal.professional.apresentation.services.office;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.repository.OfficeRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.office.factory.OfficeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllOfficeService {

    @Autowired private OfficeRepository officeRepository;
    @Autowired private OfficeFactory officeFactory;

    public Page<OfficeResponse> findAll(Pageable pageable){
        Page<Office> pageOffice = officeRepository.findAll(pageable);
        Page<OfficeResponse> pageOfficeResponse = pageOffice.map(o -> officeFactory.convertEntityInResponse(o));
        return pageOfficeResponse;
    }
}
