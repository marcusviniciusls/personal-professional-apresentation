package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.material.FindAllMaterialService;
import br.com.marcus.dev.personal.professional.apresentation.services.material.FindByIdMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value="/material")
public class MaterialController {

    @Autowired private FindAllMaterialService findAllMaterialService;
    @Autowired private FindByIdMaterialService findByIdMaterialService;

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
}