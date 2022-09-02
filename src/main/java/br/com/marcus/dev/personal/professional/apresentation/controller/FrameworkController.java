package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.FrameworkSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindAllFrameworkService;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindByIdFrameworkService;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.SaveFrameworkService;
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
@RequestMapping(value="/framework")
public class FrameworkController {

    @Autowired private FindAllFrameworkService findAllFrameworkService;
    @Autowired private FindByIdFrameworkService findByIdFrameworkService;
    @Autowired private SaveFrameworkService saveFrameworkService;

    @GetMapping
    public ResponseEntity<Page<FrameworkResponse>> findAll(Pageable page){
        Page<FrameworkResponse> frameworkResponse = findAllFrameworkService.findAll(page);
        return ResponseEntity.ok().body(frameworkResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FrameworkResponse> findById(@PathVariable UUID id){
        FrameworkResponse frameworkResponse = findByIdFrameworkService.findById(id);
        return ResponseEntity.ok().body(frameworkResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<FrameworkResponse> save(@Valid @RequestBody FrameworkSaveForm frameworkSaveForm){
        FrameworkResponse frameworkResponse = saveFrameworkService.save(frameworkSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(frameworkResponse);
    }
}
