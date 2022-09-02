package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.factory.PartnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePartnerService {

    @Autowired private PartnerFactory partnerFactory;
    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private PartnerRepository partnerRepository;

    public PartnerResponse update(PartnerRequestFormUpdate partnerRequestFormUpdate, UUID id){
        Partner partner = findByIdPartnerService.findByIdPartner(id);
        partner = partnerFactory.convertUpdateFormInEntity(partnerRequestFormUpdate,partner);
        partner = (Partner) centerEntityService.setDataToUpdate(partner);
        partner = partnerRepository.save(partner);
        PartnerResponse partnerResponse = partnerFactory.convertEntityInDto(partner);
        return partnerResponse;
    }
}
