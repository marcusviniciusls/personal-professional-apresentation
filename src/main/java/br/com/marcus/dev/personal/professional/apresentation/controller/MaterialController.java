package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.MaterialResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.material.FindAllMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/material")
public class MaterialController {

    @Autowired private FindAllMaterialService findAllMaterialService;

    @GetMapping
    public ResponseEntity<Page<MaterialResponse>> findAll(Pageable page){
        Page<MaterialResponse> MaterialResponse = findAllMaterialService.findAll(page);
        return ResponseEntity.ok().body(MaterialResponse);
    }
}
