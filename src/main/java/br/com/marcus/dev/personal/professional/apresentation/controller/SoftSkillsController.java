package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.FindAllSoftSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/softskills")
public class SoftSkillsController {

    @Autowired private FindAllSoftSkillsService findAllSoftSkillsService;

    @GetMapping
    public ResponseEntity<Page<SoftSkillsResponse>> findAll(Pageable page){
        Page<SoftSkillsResponse> softSkillsResponse = findAllSoftSkillsService.findAll(page);
        return ResponseEntity.ok().body(softSkillsResponse);
    }
}
