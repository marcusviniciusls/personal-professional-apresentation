package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.factory.PartnerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllPartnerService {

    @Autowired private PartnerRepository partnerRepository;
    @Autowired private PartnerFactory partnerFactory;

    public Page<PartnerResponse> findAll(Pageable pageable){
        Page<Partner> pagePartner = partnerRepository.findAll(pageable);
        Page<PartnerResponse> partnerResponsePage = pagePartner.map(x -> partnerFactory.convertEntityInDto(x));
        return partnerResponsePage;
    }
}
