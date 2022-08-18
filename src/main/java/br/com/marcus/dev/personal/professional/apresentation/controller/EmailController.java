package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.EmailFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.services.email.FindAllEmailService;
import br.com.marcus.dev.personal.professional.apresentation.services.email.FindByIdEmailService;
import br.com.marcus.dev.personal.professional.apresentation.services.email.SaveEmailService;
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
@RequestMapping(value="/email")
public class EmailController {

    @Autowired private FindAllEmailService findAllEmailService;
    @Autowired private FindByIdEmailService findByIdEmailService;
    @Autowired private SaveEmailService saveEmailService;

    @GetMapping
    public ResponseEntity<Page<EmailDto>> findAll(Pageable page){
        Page<EmailDto> emailDto = findAllEmailService.findAll(page);
        return ResponseEntity.ok().body(emailDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmailDto> findById(@PathVariable UUID id){
        EmailDto emailDto = findByIdEmailService.findById(id);
        return ResponseEntity.ok().body(emailDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmailDto> save(@Valid @RequestBody EmailFormSave emailFormSave){
        EmailDto emailDto = saveEmailService.save(emailFormSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(emailDto);
    }
}
