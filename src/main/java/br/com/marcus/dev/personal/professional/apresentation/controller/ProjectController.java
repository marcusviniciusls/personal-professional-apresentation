package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.project.FindAllProjectService;
import br.com.marcus.dev.personal.professional.apresentation.services.project.FindByIdProjectService;
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
@RequestMapping(value="/project")
public class ProjectController {

    @Autowired private FindAllProjectService findAllProjectService;
    @Autowired private FindByIdProjectService findByIdProjectService;

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findAll(Pageable page){
        Page<ProjectResponse> projectResponses = findAllProjectService.findAll(page);
        return ResponseEntity.ok().body(projectResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> findById(@PathVariable UUID id){
        ProjectResponse projectResponse = findByIdProjectService.findById(id);
        return ResponseEntity.ok().body(projectResponse);
    }
}
