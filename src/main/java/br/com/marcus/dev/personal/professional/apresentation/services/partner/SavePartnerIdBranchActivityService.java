package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.FindByIdBranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.factory.PartnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SavePartnerIdBranchActivityService {

    @Autowired private FindByIdBranchActivity findByIdBranchActivity;
    @Autowired private PartnerFactory partnerFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private PartnerRepository partnerRepository;
    @Autowired private BranchActivityFactory branchActivityFactory;

    public PartnerResponse save(PartnerRequestFormSave partnerRequestFormSave, UUID id){
        BranchActivity branchActivity = findByIdBranchActivity.findByIdEntity(id);
        Partner partner = partnerFactory.convertRequestFormInEntity(partnerRequestFormSave);
        partner = (Partner) centerEntityService.setDataToSave(partner);
        partner.setBranchActivity(branchActivity);
        partner = partnerRepository.save(partner);
        PartnerResponse partnerResponse = partnerFactory.convertEntityInDto(partner);
        BranchActivityResponse branchActivityResponse = branchActivityFactory.convertEntityInResponse(partner.getBranchActivity());
        partnerResponse.setBranchActivityResponse(branchActivityResponse);
        return partnerResponse;
    }
}
