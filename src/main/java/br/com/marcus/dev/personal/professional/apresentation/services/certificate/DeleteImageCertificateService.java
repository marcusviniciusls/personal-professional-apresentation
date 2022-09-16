package br.com.marcus.dev.personal.professional.apresentation.services.certificate;

import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.GetKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteImageCertificateService {

    @Autowired private FindByIdCertificateService findByIdCertificateService;
    @Autowired private GetKeyFile getKeyFile;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private DeleteFileService deleteFileService;

    public void deleteImageS3(UUID id){
        Certificate certificate = findByIdCertificateService.findByIdEntity(id);
        String urlImage = certificate.getLogoImage();
        String keyName = getKeyFile.getKeyFile(urlImage);
        certificate.setLogoImage("");
        certificate = (Certificate) centerEntityService.setDataToUpdate(certificate);
        certificateRepository.save(certificate);
        deleteFileService.deleteObjectS3(keyName);
    }
}