package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindAllCertificateService;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindByIdCertificateService;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.SaveCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value="/certificate")
public class CertificateController {

    @Autowired private FindAllCertificateService findAllCertificateService;
    @Autowired private FindByIdCertificateService findByIdCertificateService;
    @Autowired private SaveCertificateService saveCertificateService;

    @GetMapping
    public ResponseEntity<Page<CertificateResponse>> findAll(Pageable page){
        Page<CertificateResponse> certificateResponses = findAllCertificateService.findAll(page);
        return ResponseEntity.ok().body(certificateResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CertificateResponse> findById(@PathVariable UUID id){
        CertificateResponse certificateResponse = findByIdCertificateService.findById(id);
        return ResponseEntity.ok().body(certificateResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CertificateResponse> save(@Valid @RequestBody CertificateSaveForm certificateSaveForm){
        CertificateResponse certificateResponse = saveCertificateService.save(certificateSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(certificateResponse);
    }
}
