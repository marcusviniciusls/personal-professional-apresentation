package br.com.marcus.dev.personal.professional.apresentation.services.partner.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFullFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartnerFactory {

    @Autowired private BranchActivityFactory branchActivityFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ModelMapper modelMapper;

    public PartnerResponse convertEntityInDto(Partner partner){
        PartnerResponse partnerResponse = modelMapper.map(partner, PartnerResponse.class);
        return partnerResponse;
    }

    public Partner convertRequestInEntity(PartnerRequestFullFormSave partnerRequestFullFormSave){
        Partner partner = modelMapper.map(partnerRequestFullFormSave, Partner.class);
        return partner;
    }
}
