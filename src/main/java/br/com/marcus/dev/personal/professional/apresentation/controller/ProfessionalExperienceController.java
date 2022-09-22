package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.*;
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
@RequestMapping(value="/professionalexperience")
public class ProfessionalExperienceController {

    @Autowired private SaveProfessionalExperienceService saveProfessionalExperienceService;
    @Autowired private FindByIdProfessionalExperienceService findByIdProfessionalExperienceService;
    @Autowired private FindAllProfessionalExperienceService findAllProfessionalExperienceService;
    @Autowired private DeleteProfessionalExperienceService deleteProfessionalExperienceService;
    @Autowired private UpdateProfessionalExperienceService updateProfessionalExperienceService;

    @GetMapping
    public ResponseEntity<Page<ProfessionalExperienceResponse>> findAll(Pageable page){
        Page<ProfessionalExperienceResponse> response = findAllProfessionalExperienceService.findAll(page);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfessionalExperienceResponse> findById(@PathVariable UUID id){
        ProfessionalExperienceResponse response = findByIdProfessionalExperienceService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProfessionalExperienceResponse> save(@Valid @RequestBody ProfessionalExperienceFormSave request){
        ProfessionalExperienceResponse response = saveProfessionalExperienceService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TelephoneDto> update(@PathVariable UUID id, @RequestBody ProfessionalExperienceFormUpdate update){
        updateProfessionalExperienceService.update(id, update);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteProfessionalExperienceService.delete(id);
        return ResponseEntity.ok().build();
    }
}
