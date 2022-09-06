package br.com.marcus.dev.personal.professional.apresentation.services.certificate;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCertificateService {

    @Autowired private CertificateFactory certificateFactory;
    @Autowired private FindByIdCertificateService findByIdCertificateService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private CertificateRepository certificateRepository;

    public CertificateResponse update(CertificateUpdateForm certificateUpdateForm, UUID id){
        Certificate certificate = findByIdCertificateService.findByIdEntity(id);
        certificate = certificateFactory.convertUpdateFormInEntity(certificateUpdateForm, certificate);
        certificate = (Certificate) centerEntityService.setDataToUpdate(certificate);
        certificate = certificateRepository.save(certificate);
        CertificateResponse certificateResponse = certificateFactory.convertEntityInResponse(certificate);
        return certificateResponse;
    }
}
