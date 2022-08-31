package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.SaveGraduationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value="/graduation")
public class GraduationController {

    @Autowired private SaveGraduationService saveGraduationService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UUID> save(@Valid @RequestBody GraduationFormSave graduationFormSave){
        UUID idNewGraduation = saveGraduationService.save(graduationFormSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(idNewGraduation);
    }
}
