package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.SaveGraduationService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.SaveImageGraduationService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
