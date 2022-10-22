package br.com.marcus.dev.personal.professional.apresentation.services.partner.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFullFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.FindByIdBranchActivity;
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
    @Autowired private FindByIdBranchActivity findByIdBranchActivity;

    public PartnerResponse convertEntityInDto(Partner partner){
        PartnerResponse partnerResponse = modelMapper.map(partner, PartnerResponse.class);
        BranchActivityResponse branchActivityResponse = modelMapper.map(partner.getBranchActivity(), BranchActivityResponse.class);
        partnerResponse.setBranchActivityResponse(branchActivityResponse);
        return partnerResponse;
    }

    public Partner convertRequestFullFormInEntity(PartnerRequestFullFormSave partnerRequestFullFormSave){
        Partner partner = modelMapper.map(partnerRequestFullFormSave, Partner.class);
        return partner;
    }

    public Partner convertRequestFormInEntity(PartnerRequestFormSave partnerRequestFormSave){
        Partner partner = modelMapper.map(partnerRequestFormSave, Partner.class);
        return partner;
    }

    public Partner convertUpdateFormInEntity(PartnerRequestFormUpdate partnerRequestFormUpdate, Partner partner){
        if (partnerRequestFormUpdate.getName() != null && !partnerRequestFormUpdate.getName().equals("")){
            partner.setName(partnerRequestFormUpdate.getName());
        }
        if (partnerRequestFormUpdate.getDescription() != null && !partnerRequestFormUpdate.getDescription().equals("")){
            partner.setDescription(partnerRequestFormUpdate.getDescription());
        }
        if (!partner.getBranchActivity().getId().equals(partnerRequestFormUpdate.getBranchActivityId())){
            BranchActivity branchActivity = findByIdBranchActivity.findByIdEntity(partnerRequestFormUpdate.getBranchActivityId());
            partner.setBranchActivity(branchActivity);
        }
        return partner;
    }
}
