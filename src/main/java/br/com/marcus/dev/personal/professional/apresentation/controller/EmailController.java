package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.EmailDto;
import br.com.marcus.dev.personal.professional.apresentation.services.email.FindAllEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/email")
public class EmailController {

    @Autowired private FindAllEmailService findAllEmailService;

    @GetMapping
    public ResponseEntity<Page<EmailDto>> findAll(Pageable page){
        Page<EmailDto> emailDto = findAllEmailService.findAll(page);
        return ResponseEntity.ok().body(emailDto);
    }
}
