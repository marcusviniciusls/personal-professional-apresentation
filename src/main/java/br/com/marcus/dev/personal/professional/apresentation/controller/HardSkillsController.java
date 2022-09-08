package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.FindAllHardSkillsService;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.FindByIdHardSkillsService;
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
@RequestMapping(value="/hardskills")
public class HardSkillsController {

    @Autowired private FindAllHardSkillsService findAllHardSkillsService;
    @Autowired private FindByIdHardSkillsService findByIdHardSkillsService;

    @GetMapping
    public ResponseEntity<Page<HardSkillsResponse>> findAll(Pageable pageable){
        Page<HardSkillsResponse> response = findAllHardSkillsService.findAll(pageable);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<HardSkillsResponse> findById(@PathVariable UUID id){
        HardSkillsResponse hardSkillsResponse = findByIdHardSkillsService.findById(id);
        return ResponseEntity.ok().body(hardSkillsResponse);
    }
}
