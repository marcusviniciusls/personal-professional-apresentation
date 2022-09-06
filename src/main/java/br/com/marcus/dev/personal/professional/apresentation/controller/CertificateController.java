package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindAllCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/certificate")
public class CertificateController {

    @Autowired private FindAllCertificateService findAllCertificateService;

    @GetMapping
    public ResponseEntity<Page<CertificateResponse>> findAll(Pageable page){
        Page<CertificateResponse> certificateResponses = findAllCertificateService.findAll(page);
        return ResponseEntity.ok().body(certificateResponses);
    }
}
