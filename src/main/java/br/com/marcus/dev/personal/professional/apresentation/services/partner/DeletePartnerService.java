package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeletePartnerService {

    @Autowired private PartnerRepository partnerRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FindByIdPartnerService findByIdPartnerService;

    public void delete(UUID id){
        Partner partner = findByIdPartnerService.findByIdPartner(id);
        try{
            partnerRepository.deleteById(id);
        } catch(DataIntegrityViolationException ex){
            partner = (Partner) centerEntityService.setDataToDelete(partner);
            partnerRepository.save(partner);
        }
    }
}
