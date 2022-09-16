package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.project.FindAllProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/project")
public class ProjectController {

    @Autowired private FindAllProjectService findAllProjectService;

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findAll(Pageable page){
        Page<ProjectResponse> projectResponses = findAllProjectService.findAll(page);
        return ResponseEntity.ok().body(projectResponses);
    }
}
