package br.com.marcus.dev.personal.professional.apresentation.services.datapersonal;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.factory.DataPersonalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllDataPersonalService {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private DataPersonalFactory dataPersonalFactory;

    public Page<DataPersonalDto> findAll(Pageable pageable){
        Page<DataPersonal> pageDataPersonal = dataPersonalRepository.findAll(pageable);
        Page<DataPersonalDto> pageDataPersonalDto = pageDataPersonal.map(x -> dataPersonalFactory.convertEntityInDto(x));
        return pageDataPersonalDto;
    }
}
