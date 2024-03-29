package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.language.*;
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
@RequestMapping(value="/language")
public class LanguageController {

    @Autowired private FindByIdLanguageService findByIdLanguageService;
    @Autowired private FindAllLanguageService findAllLanguageService;
    @Autowired private SaveLanguageService saveLanguageService;
    @Autowired private DeleteLanguageService deleteLanguageService;
    @Autowired private UpdateLanguageService updateLanguageService;

    @GetMapping
    public ResponseEntity<Page<LanguageResponse>> findAll(Pageable page){
        Page<LanguageResponse> languageResponses = findAllLanguageService.findAll(page);
        return ResponseEntity.ok().body(languageResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LanguageResponse> findById(@PathVariable UUID id){
        LanguageResponse languageResponse = findByIdLanguageService.findById(id);
        return ResponseEntity.ok().body(languageResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LanguageResponse> save(@Valid @RequestBody LanguageSaveForm languageSaveForm){
        LanguageResponse languageResponse = saveLanguageService.save(languageSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(languageResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody LanguageUpdateForm languageUpdateForm){
        updateLanguageService.update(id, languageUpdateForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteLanguageService.delete(id);
        return ResponseEntity.ok().build();
    }
}