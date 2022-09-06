package br.com.marcus.dev.personal.professional.apresentation.services.certificate;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.factory.CertificateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdCertificateService {

    @Autowired private CertificateRepository certificateRepository;
    @Autowired private CertificateFactory certificateFactory;

    public CertificateResponse findById(UUID id){
        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
        if (optionalCertificate.isEmpty()){
            throw new ResourceNotFoundException("CERTIFICATE NOT FOUND EXCEPTION");
        }
        if (!optionalCertificate.get().isStatus()){
            throw new ResourceNotFoundException("CERTIFICATE NOT FOUND EXCEPTION");
        }
        Certificate certificate = optionalCertificate.get();
        CertificateResponse certificateResponse = certificateFactory.convertEntityInResponse(certificate);
        return certificateResponse;
    }

    public Certificate findByIdEntity(UUID id){
        Optional<Certificate> optionalCertificate = certificateRepository.findById(id);
        if (optionalCertificate.isEmpty()){
            throw new ResourceNotFoundException("CERTIFICATE NOT FOUND EXCEPTION");
        }
        if (!optionalCertificate.get().isStatus()){
            throw new ResourceNotFoundException("CERTIFICATE NOT FOUND EXCEPTION");
        }
        Certificate certificate = optionalCertificate.get();
        return certificate;
    }
}
