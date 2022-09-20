package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.MaterialSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.material.FindAllMaterialService;
import br.com.marcus.dev.personal.professional.apresentation.services.material.FindByIdMaterialService;
import br.com.marcus.dev.personal.professional.apresentation.services.material.SaveMaterialService;
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
}