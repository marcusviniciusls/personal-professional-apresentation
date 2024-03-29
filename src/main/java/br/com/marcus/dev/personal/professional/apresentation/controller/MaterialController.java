package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.material.*;
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
@RequestMapping(value="/material")
public class MaterialController {

    @Autowired private FindAllMaterialService findAllMaterialService;
    @Autowired private FindByIdMaterialService findByIdMaterialService;
    @Autowired private SaveMaterialService saveMaterialService;
    @Autowired private DeleteMaterialService deleteMaterialService;
    @Autowired private UpdateMaterialService updateMaterialService;

    @GetMapping
    public ResponseEntity<Page<MaterialResponse>> findAll(Pageable page){
        Page<MaterialResponse> MaterialResponse = findAllMaterialService.findAll(page);
        return ResponseEntity.ok().body(MaterialResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MaterialResponse> findById(@PathVariable UUID id){
        MaterialResponse materialResponse = findByIdMaterialService.findById(id);
        return ResponseEntity.ok().body(materialResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MaterialResponse> save(@Valid @RequestBody MaterialSaveForm materialSaveForm){
        MaterialResponse materialResponse = saveMaterialService.save(materialSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody MaterialUpdateForm materialUpdateForm){
        updateMaterialService.update(id, materialUpdateForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteMaterialService.delete(id);
        return ResponseEntity.ok().build();
    }
}