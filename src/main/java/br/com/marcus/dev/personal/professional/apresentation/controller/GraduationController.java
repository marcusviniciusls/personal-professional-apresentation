package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.GraduationResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.*;
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
@RequestMapping(value="/graduation")
public class GraduationController {

    @Autowired private SaveGraduationService saveGraduationService;
    @Autowired private SaveImageGraduationService saveImageGraduationService;
    @Autowired private FindAllGraduationService findAllGraduationService;
    @Autowired private FindByIdGraduationService findByIdGraduationService;
    @Autowired private DeleteGraduationService deleteGraduationService;
    @Autowired private UpdateGraduationService updateGraduationService;

    @GetMapping
    public ResponseEntity<Page<GraduationResponse>> findAll(Pageable page){
        Page<GraduationResponse> partnerResponse = findAllGraduationService.findAllGraduation(page);
        return ResponseEntity.ok().body(partnerResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GraduationResponse> findById(@PathVariable UUID id){
        GraduationResponse graduationResponse = findByIdGraduationService.findById(id);
        return ResponseEntity.ok().body(graduationResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UUID> save(@Valid @RequestBody GraduationFormSave graduationFormSave){
        UUID idNewGraduation = saveGraduationService.save(graduationFormSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(idNewGraduation);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/{id}")
    public ResponseEntity<?> saveImage(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable UUID id){
        saveImageGraduationService.saveimage(multipartFile, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<GraduationResponse> update(@PathVariable UUID id, @Valid @RequestBody GraduationFormUpdate graduationFormUpdate){
        GraduationResponse graduationResponse = updateGraduationService.update(graduationFormUpdate, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(graduationResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteGraduationService.delete(id);
        return ResponseEntity.ok().build();
    }
}