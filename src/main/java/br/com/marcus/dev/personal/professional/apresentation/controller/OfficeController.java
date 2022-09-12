package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.OfficeSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.services.office.FindAllOfficeService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.FindByIdOfficeService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.SaveOfficeService;
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
@RequestMapping(value="/office")
public class OfficeController {

    @Autowired private FindAllOfficeService findAllOfficeService;
    @Autowired private FindByIdOfficeService findByIdOfficeService;
    @Autowired private SaveOfficeService saveOfficeService;

    @GetMapping
    public ResponseEntity<Page<OfficeResponse>> findAll(Pageable page){
        Page<OfficeResponse> officeResponse = findAllOfficeService.findAll(page);
        return ResponseEntity.ok().body(officeResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OfficeResponse> findById(@PathVariable UUID id){
        OfficeResponse officeResponse = findByIdOfficeService.findById(id);
        return ResponseEntity.ok().body(officeResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OfficeResponse> save(@Valid @RequestBody OfficeSaveForm officeSaveForm){
        OfficeResponse officeResponse = saveOfficeService.save(officeSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(officeResponse);
    }
}
