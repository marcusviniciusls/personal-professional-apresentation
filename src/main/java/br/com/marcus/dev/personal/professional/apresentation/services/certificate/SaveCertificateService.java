package br.com.marcus.dev.personal.professional.apresentation.services.certificate;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveCertificateService {

    @Autowired private CertificateFactory certificateFactory;
    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private SaveActivitiesService saveActivitiesService;

    public CertificateResponse save(CertificateSaveForm certificateSaveForm){
        Certificate certificate = certificateFactory.convertSaveFormInEntity(certificateSaveForm);
        Partner partner = findByIdPartnerService.findByIdPartner(certificateSaveForm.getPartnerId());
        certificate.setPartner(partner);
        certificate = (Certificate) centerEntityService.setDataToSave(certificate);
        certificate = certificateRepository.save(certificate);
        saveActivitiesService.saveMovementCertificate(certificate);
        CertificateResponse certificateResponse = certificateFactory.convertEntityInResponse(certificate);
        return certificateResponse;
    }
}
