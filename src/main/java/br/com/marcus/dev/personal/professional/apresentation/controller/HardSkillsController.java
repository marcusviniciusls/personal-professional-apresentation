package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.HardSkillsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.*;
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
@RequestMapping(value="/hardskills")
public class HardSkillsController {

    @Autowired private FindAllHardSkillsService findAllHardSkillsService;
    @Autowired private FindByIdHardSkillsService findByIdHardSkillsService;
    @Autowired private SaveHardSkillsService saveHardSkillsService;
    @Autowired private DeleteHardSkillsService deleteHardSkillsService;
    @Autowired private UpdateHardSkillsService updateHardSkillsService;

    @GetMapping
    public ResponseEntity<Page<HardSkillsResponse>> findAll(Pageable pageable){
        Page<HardSkillsResponse> response = findAllHardSkillsService.findAll(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HardSkillsResponse> findById(@PathVariable UUID id){
        HardSkillsResponse hardSkillsResponse = findByIdHardSkillsService.findById(id);
        return ResponseEntity.ok().body(hardSkillsResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<HardSkillsResponse> save(@Valid @RequestBody HardSkillsSaveForm hardSkillsSaveForm){
        HardSkillsResponse hardSkillsResponse = saveHardSkillsService.save(hardSkillsSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(hardSkillsResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody HardSkillsUpdateForm hardSkillsUpdateForm){
        updateHardSkillsService.update(id, hardSkillsUpdateForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteHardSkillsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
