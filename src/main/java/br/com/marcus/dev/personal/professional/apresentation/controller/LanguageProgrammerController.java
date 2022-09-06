package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.FindAllLanguageProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/languageProgrammer")
public class LanguageProgrammerController {

    @Autowired private FindAllLanguageProgrammerService findAllLanguageProgrammerService;

    @GetMapping
    public ResponseEntity<Page<LanguageProgrammerResponse>> findAll(Pageable pageable){
        Page<LanguageProgrammerResponse> response = findAllLanguageProgrammerService.findAll(pageable);
        return ResponseEntity.ok().body(response);
    }
}
