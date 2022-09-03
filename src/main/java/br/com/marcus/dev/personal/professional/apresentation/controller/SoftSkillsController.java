package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SoftSkillsFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.*;
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
@RequestMapping(value="/softskills")
public class SoftSkillsController {

    @Autowired private FindAllSoftSkillsService findAllSoftSkillsService;
    @Autowired private FindByIdSoftSkillsService findByIdSoftSkillsService;
    @Autowired private SaveSoftSkillsService saveSoftSkillsService;
    @Autowired private DeleteSoftSkillsService deleteSoftSkillsService;
    @Autowired private UpdateSoftSkillsService updateSoftSkillsService;
    @Autowired private DisableSoftSkillsService disableSoftSkillsService;
    @Autowired private EnableSoftSkillsService enableSoftSkillsService;

    @GetMapping
    public ResponseEntity<Page<SoftSkillsResponse>> findAll(Pageable page){
        Page<SoftSkillsResponse> softSkillsResponse = findAllSoftSkillsService.findAll(page);
        return ResponseEntity.ok().body(softSkillsResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SoftSkillsResponse> findById(@PathVariable UUID id){
        SoftSkillsResponse softSkillsResponse = findByIdSoftSkillsService.findById(id);
        return ResponseEntity.ok().body(softSkillsResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SoftSkillsResponse> save(@Valid @RequestBody SoftSkillsFormSave softSkillsFormSave){
        SoftSkillsResponse softSkillsResponse = saveSoftSkillsService.save(softSkillsFormSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(softSkillsResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SoftSkillsResponse> update(@PathVariable UUID id, @Valid @RequestBody SoftSkillsFormUpdate softSkillsFormUpdate){
        SoftSkillsResponse softSkillsResponse = updateSoftSkillsService.update(softSkillsFormUpdate, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(softSkillsResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/disable/{id}")
    public ResponseEntity<?> updateDisable(@PathVariable UUID id){
        disableSoftSkillsService.disable(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/enable/{id}")
    public ResponseEntity<?> updateEnable(@PathVariable UUID id){
        enableSoftSkillsService.enable(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteSoftSkillsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
