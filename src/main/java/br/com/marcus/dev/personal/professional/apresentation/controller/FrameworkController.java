package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindAllFrameworkService;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.FindByIdFrameworkService;
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
@RequestMapping(value="/framework")
public class FrameworkController {

    @Autowired private FindAllFrameworkService findAllFrameworkService;
    @Autowired private FindByIdFrameworkService findByIdFrameworkService;

    @GetMapping
    public ResponseEntity<Page<FrameworkResponse>> findAll(Pageable page){
        Page<FrameworkResponse> frameworkResponse = findAllFrameworkService.findAll(page);
        return ResponseEntity.ok().body(frameworkResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FrameworkResponse> findById(@PathVariable UUID id){
        FrameworkResponse frameworkResponse = findByIdFrameworkService.findById(id);
        return ResponseEntity.ok().body(frameworkResponse);
    }
}