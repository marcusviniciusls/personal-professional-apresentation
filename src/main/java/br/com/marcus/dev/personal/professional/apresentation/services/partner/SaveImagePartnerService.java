package br.com.marcus.dev.personal.professional.apresentation.services.partner;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveImagePartnerService {

    @Autowired private PartnerRepository partnerRepository;
    @Autowired private SendFileAwsS3 sendFileAwsS3;

    public void saveimage(MultipartFile multipartFile, UUID idPartner){
        Partner partner = partnerRepository.findById(idPartner).get();
        if (partner.getUrlImage() != null && !partner.getUrlImage().equals("")){
            throw new FileException("RESOURCE IS ALREADY SAVED");
        }
        String urlImage = sendFileAwsS3.uploadFile(multipartFile).toString();
        partner.setUrlImage(urlImage);
        partnerRepository.save(partner);
    }
}
