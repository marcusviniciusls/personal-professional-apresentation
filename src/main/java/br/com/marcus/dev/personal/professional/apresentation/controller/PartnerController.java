package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartnerRequestFullFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindAllPartnerService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.SavePartnerService;
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
@RequestMapping(value="/partner")
public class PartnerController {

    @Autowired private FindAllPartnerService findAllPartnerService;
    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private SavePartnerService savePartnerService;

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
}
