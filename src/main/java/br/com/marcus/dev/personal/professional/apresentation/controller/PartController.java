package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.part.FindAllPartService;
import br.com.marcus.dev.personal.professional.apresentation.services.part.FindByIdPartService;
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
@RequestMapping(value="/part")
public class PartController {

    @Autowired private FindAllPartService findAllPartService;
    @Autowired private FindByIdPartService findByIdPartService;

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
}
