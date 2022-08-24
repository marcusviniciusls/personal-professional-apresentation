package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.factory.PartnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdPartnerService {

    @Autowired private PartnerRepository partnerRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private PartnerFactory partnerFactory;

    public PartnerResponse findById(UUID id){
        Optional<Partner> optionalPartner = partnerRepository.findById(id);
        if (optionalPartner.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        Partner partner = optionalPartner.get();
        if (!centerEntityService.isStatusSuperEntity(partner)){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        PartnerResponse partnerResponse = partnerFactory.convertEntityInDto(partner);
        return partnerResponse;
    }
}
