package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.language.FindByIdLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value="/language")
public class LanguageController {

    @Autowired private FindByIdLanguageService findByIdLanguageService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LanguageResponse> findById(@PathVariable UUID id){
        LanguageResponse languageResponse = findByIdLanguageService.findById(id);
        return ResponseEntity.ok().body(languageResponse);
    }
}