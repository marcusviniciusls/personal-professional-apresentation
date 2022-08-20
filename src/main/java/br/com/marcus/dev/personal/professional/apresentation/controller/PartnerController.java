package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindAllPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/partner")
public class PartnerController {

    @Autowired private FindAllPartnerService findAllPartnerService;

    @GetMapping
    public ResponseEntity<Page<PartnerResponse>> findAll(Pageable page){
        Page<PartnerResponse> partnerResponse = findAllPartnerService.findAll(page);
        return ResponseEntity.ok().body(partnerResponse);
    }
}
