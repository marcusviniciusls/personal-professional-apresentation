package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.project.*;
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
@RequestMapping(value="/project")
public class ProjectController {

    @Autowired private FindAllProjectService findAllProjectService;
    @Autowired private FindByIdProjectService findByIdProjectService;
    @Autowired private SaveProjectService saveProjectService;
    @Autowired private DeleteProjectService deleteProjectService;
    @Autowired private UpdateProjectService updateProjectService;

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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProjectResponse> save(@Valid @RequestBody ProjectSaveForm projectSaveForm){
        ProjectResponse projectResponse = saveProjectService.save(projectSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable UUID id, @Valid @RequestBody ProjectUpdateForm projectUpdateForm){
        ProjectResponse projectResponse = updateProjectService.update(id, projectUpdateForm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(projectResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteProjectService.delete(id);
        return ResponseEntity.ok().build();
    }
}