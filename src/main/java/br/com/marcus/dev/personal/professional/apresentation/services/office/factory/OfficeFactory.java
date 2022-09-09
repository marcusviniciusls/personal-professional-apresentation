package br.com.marcus.dev.personal.professional.apresentation.services.office.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficeFactory {

    @Autowired private ModelMapper modelMapper;

    public OfficeResponse convertEntityInResponse(Office office){
        return modelMapper.map(office, OfficeResponse.class);
    }
}
