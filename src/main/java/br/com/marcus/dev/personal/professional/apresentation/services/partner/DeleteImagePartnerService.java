package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.GetKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteImagePartnerService {

    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private DeleteFileService deleteFileService;
    @Autowired private GetKeyFile getKeyFile;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private PartnerRepository partnerRepository;

    public void deleteImageS3(UUID id){
        Partner partner = findByIdPartnerService.findByIdPartner(id);
        String urlImage = partner.getUrlImage();
        String keyName = getKeyFile.getKeyFile(urlImage);
        partner.setUrlImage("");
        partner = (Partner) centerEntityService.setDataToUpdate(partner);
        partnerRepository.save(partner);
        deleteFileService.deleteObjectS3(keyName);
    }
}
