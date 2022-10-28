package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerSaveFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.LanguageProgrammerUpdateFrom;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.*;
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
@RequestMapping(value="/languageProgrammer")
public class LanguageProgrammerController {

    @Autowired private FindAllLanguageProgrammerService findAllLanguageProgrammerService;
    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;
    @Autowired private SaveLanguageProgrammerService saveLanguageProgrammerService;
    @Autowired private DeleteLanguageProgrammerService deleteLanguageProgrammerService;
    @Autowired private UpdateLanguageProgrammerService updateLanguageProgrammerService;
    @Autowired private SaveImageLanguageProgrammerService saveImageLanguageProgrammerService;
    @Autowired private DeleteImageLanguageProgrammerService deleteImageLanguageProgrammerService;

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
    @PostMapping(value = "/{id}/image")
    public ResponseEntity<?> saveImage(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable UUID id){
        saveImageLanguageProgrammerService.saveImage(multipartFile, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<LanguageProgrammerResponse> update(@PathVariable UUID id, @Valid @RequestBody LanguageProgrammerUpdateFrom languageProgrammerUpdateFrom){
        LanguageProgrammerResponse response = updateLanguageProgrammerService.update(id, languageProgrammerUpdateFrom);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteLanguageProgrammerService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}/image")
    public ResponseEntity<?> deleteImage(@PathVariable UUID id){
        deleteImageLanguageProgrammerService.deleteImageS3(id);
        return ResponseEntity.ok().build();
    }
}
