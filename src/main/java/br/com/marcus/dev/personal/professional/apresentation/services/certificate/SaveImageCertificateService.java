package br.com.marcus.dev.personal.professional.apresentation.services.certificate;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindByIdFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveImageCertificateService {

    @Autowired private FindByIdCertificateService findByIdCertificateService;
    @Autowired private SendFileAwsS3 sendFileAwsS3;
    @Autowired private CertificateRepository certificateRepository;

    public void saveImageCertificate(MultipartFile multipartFile, UUID idCertificate){
        Certificate certificate = findByIdCertificateService.findByIdEntity(idCertificate);
        if (certificate.getLogoImage() != null && !certificate.getLogoImage().equals("")){
            throw new FileException("RESOURCE IS ALREADY SAVED");
        }
        String url = sendFileAwsS3.uploadFile(multipartFile).toString();
        certificate.setLogoImage(url);
        certificateRepository.save(certificate);
    }
}
