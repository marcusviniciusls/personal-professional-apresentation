package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindAllLanguageProgrammerService;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindByIdLanguageProgrammerService;
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
@RequestMapping(value="/languageProgrammer")
public class LanguageProgrammerController {

    @Autowired private FindAllLanguageProgrammerService findAllLanguageProgrammerService;
    @Autowired private FindByIdLanguageProgrammerService findByIdLanguageProgrammerService;

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
}
