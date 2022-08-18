package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.FindAllTelephoneService;
import br.com.marcus.dev.personal.professional.apresentation.services.telephone.FindByIdTelephoneService;
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
@RequestMapping(value="/telephone")
public class TelephoneController {

    @Autowired private FindAllTelephoneService findAllTelephoneService;
    @Autowired private FindByIdTelephoneService findByIdTelephoneService;

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
}