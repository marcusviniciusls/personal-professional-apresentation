package br.com.marcus.dev.personal.professional.apresentation.services.part;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Part;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.part.factory.PartFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllPartService {

    @Autowired private PartRepository partRepository;
    @Autowired private PartFactory partFactory;

    public Page<PartResponse> findAll(Pageable pageable){
        Page<Part> pagePart = partRepository.findAll(pageable);
        Page<PartResponse> pagePartResponse = pagePart.map(p -> partFactory.convertEntityInResponse(p));
        return pagePartResponse;
    }
}
