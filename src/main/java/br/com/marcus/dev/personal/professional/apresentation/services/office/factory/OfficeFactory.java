package br.com.marcus.dev.personal.professional.apresentation.services.office.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeUpdateForm;
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

    public Office convertUpdateFormInEntity(OfficeUpdateForm officeUpdateForm, Office office){
        if (officeUpdateForm.getName() != null && !officeUpdateForm.getName().equals("")){
            office.setName(officeUpdateForm.getName());
        }
        if (officeUpdateForm.getDescription() != null && !officeUpdateForm.getDescription().equals("")){
            office.setDescription(officeUpdateForm.getDescription());
        }
        if (officeUpdateForm.getOfficeLevel() != null){
            office.setOfficeLevel(OfficeLevel.toEnum(officeUpdateForm.getOfficeLevel()));
        }
        return office;
    }
}
