package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.*;
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
@RequestMapping(value="/telephone")
public class TelephoneController {

    @Autowired private FindAllTelephoneService findAllTelephoneService;
    @Autowired private FindByIdTelephoneService findByIdTelephoneService;
    @Autowired private SaveTelephoneService saveTelephoneService;
    @Autowired private UpdateTelephoneService updateTelephoneService;
    @Autowired private DeleteTelephoneService deleteTelephoneService;

    @GetMapping
    public ResponseEntity<Page<TelephoneDto>> findAll(Pageable page){
        Page<TelephoneDto> telephoneDto = findAllTelephoneService.findAll(page);
        return ResponseEntity.ok().body(telephoneDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TelephoneDto> findById(@PathVariable UUID id){
        TelephoneDto telephoneDto = findByIdTelephoneService.findById(id);
        return ResponseEntity.ok().body(telephoneDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TelephoneDto> save(@Valid @RequestBody TelephoneFormSave telephoneFormSave){
        TelephoneDto telephoneDto = saveTelephoneService.save(telephoneFormSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(telephoneDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TelephoneDto> update(@PathVariable UUID id, @Valid @RequestBody TelephoneFormUpdate telephoneFormUpdate){
        TelephoneDto telephoneDto = updateTelephoneService.update(telephoneFormUpdate, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(telephoneDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteTelephoneService.delete(id);
        return ResponseEntity.ok().build();
    }
}