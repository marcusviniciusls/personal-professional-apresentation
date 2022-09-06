package br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CertificateFactory {

    @Autowired private ModelMapper modelMapper;

    public CertificateResponse convertEntityInResponse(Certificate certificate){
        CertificateResponse certificateResponse = modelMapper.map(certificate, CertificateResponse.class);
        PartnerResponse partnerResponse = modelMapper.map(certificate.getPartner(), PartnerResponse.class);
        certificateResponse.setPartnerResponse(partnerResponse);
        BranchActivityResponse branchActivityResponse = new BranchActivityResponse(certificate.getPartner().getBranchActivity().getName());
        partnerResponse.setBranchActivityResponse(branchActivityResponse);
        return certificateResponse;
    }
}
