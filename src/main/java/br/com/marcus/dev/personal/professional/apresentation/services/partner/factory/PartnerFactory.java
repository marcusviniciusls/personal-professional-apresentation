package br.com.marcus.dev.personal.professional.apresentation.services.partner.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.services.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartnerFactory {

    @Autowired private BranchActivityFactory branchActivityFactory;
    @Autowired private CenterEntityService centerEntityService;

    public PartnerResponse convertEntityInDto(Partner partner){
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setDescription(partner.getDescription());
        partnerResponse.setName(partner.getName());
        partnerResponse.setUrlImage(partner.getUrlImage());
        if (centerEntityService.isStatusSuperEntity(partner.getBranchActivity())){
            BranchActivityResponse branchActivityResponse = branchActivityFactory.convertEntityInResponse(partner.getBranchActivity());
            partnerResponse.setBranchActivityResponse(branchActivityResponse);
        }
        return partnerResponse;
    }
}
