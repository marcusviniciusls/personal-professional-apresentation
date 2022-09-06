package br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private FindByIdPartnerService findByIdPartnerService;

    public CertificateResponse convertEntityInResponse(Certificate certificate){
        CertificateResponse certificateResponse = modelMapper.map(certificate, CertificateResponse.class);
        PartnerResponse partnerResponse = modelMapper.map(certificate.getPartner(), PartnerResponse.class);
        certificateResponse.setPartnerResponse(partnerResponse);
        BranchActivityResponse branchActivityResponse = new BranchActivityResponse(certificate.getPartner().getBranchActivity().getName());
        partnerResponse.setBranchActivityResponse(branchActivityResponse);
        return certificateResponse;
    }

    public Certificate convertSaveFormInEntity(CertificateSaveForm certificateSaveForm){
        Certificate certificate = new Certificate();
        certificate.setName(certificateSaveForm.getName());
        return certificate;
    }

    public Certificate convertUpdateFormInEntity(CertificateUpdateForm certificateUpdateForm, Certificate certificate){
        if (certificateUpdateForm.getName() != null){
            certificate.setName(certificateUpdateForm.getName());
        }
        if (certificateUpdateForm.getPartnerId() != null){
            Partner partner = findByIdPartnerService.findByIdPartner(certificateUpdateForm.getPartnerId());
            certificate.setPartner(partner);
        }
        return certificate;
    }
}
