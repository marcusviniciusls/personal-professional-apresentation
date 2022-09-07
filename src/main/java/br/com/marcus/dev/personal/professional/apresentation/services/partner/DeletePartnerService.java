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

    public void delete(UUID id){
        Optional<Partner> optionalPartner = partnerRepository.findById(id);
        if (optionalPartner.isEmpty()){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        if (!centerEntityService.isStatusSuperEntity(optionalPartner.get())){
            throw new ResourceNotFoundException("ID Not Found Exception");
        }
        try{
            partnerRepository.deleteById(id);
        } catch(DataIntegrityViolationException ex){
            Partner partner = optionalPartner.get();
            partner = (Partner) centerEntityService.setDataToDelete(partner);
            partnerRepository.save(partner);
        }
    }
}
