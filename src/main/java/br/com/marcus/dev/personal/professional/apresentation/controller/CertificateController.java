package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value="/certificate")
public class CertificateController {

    @Autowired private FindAllCertificateService findAllCertificateService;
    @Autowired private FindByIdCertificateService findByIdCertificateService;
    @Autowired private SaveCertificateService saveCertificateService;
    @Autowired private DeleteCertificateService deleteCertificateService;
    @Autowired private UpdateCertificateService updateCertificateService;
    @Autowired private SaveImageCertificateService saveImageCertificateService;
    @Autowired private DeleteImageCertificateService deleteImageCertificateService;

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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/{id}/image")
    public ResponseEntity<?> saveImage(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable UUID id){
        saveImageCertificateService.saveImageCertificate(multipartFile, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CertificateResponse> update(@PathVariable UUID id, @Valid @RequestBody CertificateUpdateForm certificateUpdateForm){
        CertificateResponse certificateResponse = updateCertificateService.update(certificateUpdateForm, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(certificateResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteCertificateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}/image")
    public ResponseEntity<?> deleteImage(@PathVariable UUID id){
        deleteImageCertificateService.deleteImageS3(id);
        return ResponseEntity.ok().build();
    }
}
