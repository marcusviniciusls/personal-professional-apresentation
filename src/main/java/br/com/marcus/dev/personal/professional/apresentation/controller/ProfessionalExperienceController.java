package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.FindAllProfessionalExperienceService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.FindByIdProfessionalExperienceService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.SaveProfessionalExperienceService;
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
}
