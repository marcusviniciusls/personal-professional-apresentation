package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.FindAllSoftSkillsService;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.FindByIdSoftSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value="/softskills")
public class SoftSkillsController {

    @Autowired private FindAllSoftSkillsService findAllSoftSkillsService;
    @Autowired private FindByIdSoftSkillsService findByIdSoftSkillsService;

    @GetMapping
    public ResponseEntity<Page<SoftSkillsResponse>> findAll(Pageable page){
        Page<SoftSkillsResponse> softSkillsResponse = findAllSoftSkillsService.findAll(page);
        return ResponseEntity.ok().body(softSkillsResponse);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<SoftSkillsResponse> findById(@PathVariable UUID id){
        SoftSkillsResponse softSkillsResponse = findByIdSoftSkillsService.findById(id);
        return ResponseEntity.ok().body(softSkillsResponse);
    }
}
