package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFullFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.*;
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
@RequestMapping(value="/partner")
public class PartnerController {

    @Autowired private FindAllPartnerService findAllPartnerService;
    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private SavePartnerService savePartnerService;
    @Autowired private SavePartnerIdBranchActivityService savePartnerIdBranchActivityService;
    @Autowired private UpdatePartnerService updatePartnerService;
    @Autowired private DeletePartnerService deletePartnerService;
    @Autowired private SaveImagePartnerService saveImagePartnerService;
    @Autowired private DeleteImagePartnerService deleteImagePartnerService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<PartnerResponse>> findAll(Pageable page){
        Page<PartnerResponse> partnerResponse = findAllPartnerService.findAll(page);
        return ResponseEntity.ok().body(partnerResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PartnerResponse> findById(@PathVariable UUID id){
        PartnerResponse partnerResponse = findByIdPartnerService.findById(id);
        return ResponseEntity.ok().body(partnerResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PartnerResponse> save(@Valid @RequestBody PartnerRequestFullFormSave request){
        PartnerResponse partnerResponse = savePartnerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(partnerResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/{id}")
    public ResponseEntity<PartnerResponse> saveWithBranch(@Valid @RequestBody PartnerRequestFormSave request, @PathVariable UUID id){
        PartnerResponse partnerResponse = savePartnerIdBranchActivityService.save(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(partnerResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/{id}/image")
    public ResponseEntity<?> saveImage(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable UUID id){
        saveImagePartnerService.saveimage(multipartFile, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PartnerResponse> update(@PathVariable UUID id, @Valid @RequestBody PartnerRequestFormUpdate partnerRequestFormUpdate){
        PartnerResponse partnerResponse = updatePartnerService.update(partnerRequestFormUpdate, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(partnerResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deletePartnerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}/image")
    public ResponseEntity<?> deleteImage(@PathVariable UUID id){
        deleteImagePartnerService.deleteImageS3(id);
        return ResponseEntity.ok().build();
    }
}
