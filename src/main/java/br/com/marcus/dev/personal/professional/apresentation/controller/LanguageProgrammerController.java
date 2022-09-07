package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerSaveFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.DeleteLanguageProgrammerService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindAllLanguageProgrammerService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindByIdLanguageProgrammerService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.SaveLanguageProgrammerService;
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
@RequestMapping(value="/languageProgrammer")
public class LanguageProgrammerController {

    @Autowired private FindAllLanguageProgrammerService findAllLanguageProgrammerService;
    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;
    @Autowired private SaveLanguageProgrammerService saveLanguageProgrammerService;
    @Autowired private DeleteLanguageProgrammerService deleteLanguageProgrammerService;

    @GetMapping
    public ResponseEntity<Page<LanguageProgrammerResponse>> findAll(Pageable pageable){
        Page<LanguageProgrammerResponse> response = findAllLanguageProgrammerService.findAll(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LanguageProgrammerResponse> findById(@PathVariable UUID id){
        LanguageProgrammerResponse languageProgrammerResponse = findByIdLanguageProgrammerService.findById(id);
        return ResponseEntity.ok().body(languageProgrammerResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LanguageProgrammerResponse> save(@Valid @RequestBody LanguageProgrammerSaveFrom languageProgrammerSaveFrom){
        LanguageProgrammerResponse languageProgrammerResponse = saveLanguageProgrammerService.save(languageProgrammerSaveFrom);
        return ResponseEntity.status(HttpStatus.CREATED).body(languageProgrammerResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteLanguageProgrammerService.delete(id);
        return ResponseEntity.ok().build();
    }
}
