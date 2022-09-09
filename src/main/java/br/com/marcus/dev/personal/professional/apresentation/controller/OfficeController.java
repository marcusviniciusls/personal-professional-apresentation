package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.office.FindAllOfficeService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.FindByIdOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value="/office")
public class OfficeController {

    @Autowired private FindAllOfficeService findAllOfficeService;
    @Autowired private FindByIdOfficeService findByIdOfficeService;

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
}
