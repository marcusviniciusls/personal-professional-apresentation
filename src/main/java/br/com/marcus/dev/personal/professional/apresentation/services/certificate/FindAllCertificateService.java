package br.com.marcus.dev.personal.professional.apresentation.services.certificate;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllCertificateService {

    @Autowired private CertificateRepository certificateRepository;
    @Autowired private CertificateFactory certificateFactory;

    public Page<CertificateResponse> findAll(Pageable pageable){
        Page<Certificate> pageCertificate = certificateRepository.findAll(pageable);
        Page<CertificateResponse> pageCertificateResponse = pageCertificate.map(c -> certificateFactory.convertEntityInResponse(c));
        return pageCertificateResponse;
    }
}
