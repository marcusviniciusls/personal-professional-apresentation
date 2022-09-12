package br.com.marcus.dev.personal.professional.apresentation.services.office.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfficeFactory {

    @Autowired private ModelMapper modelMapper;

    public OfficeResponse convertEntityInResponse(Office office){
        return modelMapper.map(office, OfficeResponse.class);
    }

    public Office convertSaveFormInEntity(OfficeSaveForm officeSaveForm){
        Office office = new Office();
        office.setName(officeSaveForm.getName());
        office.setDescription(officeSaveForm.getDescription());
        office.setOfficeLevel(OfficeLevel.toEnum(officeSaveForm.getOfficeLevel()));
        return office;
    }
}
