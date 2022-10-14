package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFullFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.SaveBranchActivityService;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.factory.PartnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SavePartnerService {

    @Autowired private PartnerRepository partnerRepository;
    @Autowired private PartnerFactory partnerFactory;
    @Autowired private SaveBranchActivityService saveBranchActivityService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private BranchActivityFactory branchActivityFactory;

    public PartnerResponse save(PartnerRequestFullFormSave request){
        BranchActivity branchActivity = new BranchActivity();
        if (request.getBranchActivity() != null){
            String name = request.getName();
            BranchActivityForm branchActivityForm = new BranchActivityForm(name);
            branchActivity = saveBranchActivityService.saveEntity(branchActivityForm);
        }
        Partner partner = partnerFactory.convertRequestFullFormInEntity(request);
        partner.setUser(branchActivity.getUser());
        partner.setDateCreation(branchActivity.getDateCreation());
        partner.setUserCreation(branchActivity.getUserCreation());
        partner.setBranchActivity(branchActivity);
        partner = partnerRepository.save(partner);
        PartnerResponse partnerResponse = partnerFactory.convertEntityInDto(partner);
        BranchActivityResponse branchActivityResponse = branchActivityFactory.convertEntityInResponse(branchActivity);
        partnerResponse.setBranchActivityResponse(branchActivityResponse);
        return partnerResponse;
    }
}
