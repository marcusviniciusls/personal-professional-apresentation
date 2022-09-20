package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.PartSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.part.DeletePartService;
import br.com.marcus.dev.personal.professional.apresentation.services.part.FindAllPartService;
import br.com.marcus.dev.personal.professional.apresentation.services.part.FindByIdPartService;
import br.com.marcus.dev.personal.professional.apresentation.services.part.SavePartService;
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
@RequestMapping(value="/part")
public class PartController {

    @Autowired private FindAllPartService findAllPartService;
    @Autowired private FindByIdPartService findByIdPartService;
    @Autowired private SavePartService savePartService;
    @Autowired private DeletePartService deletePartService;

    @GetMapping
    public ResponseEntity<Page<PartResponse>> findAll(Pageable page){
        Page<PartResponse> partResponses = findAllPartService.findAll(page);
        return ResponseEntity.ok().body(partResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PartResponse> findById(@PathVariable UUID id){
        PartResponse partResponse = findByIdPartService.findById(id);
        return ResponseEntity.ok().body(partResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PartResponse> save(@Valid @RequestBody PartSaveForm partSaveForm){
        PartResponse partResponse = savePartService.save(partSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(partResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deletePartService.delete(id);
        return ResponseEntity.ok().build();
    }
}
