package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveAddForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.*;
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
@RequestMapping(value = "/assignments")
public class AssignmentsController {

    @Autowired private SaveAssignmentsService saveAssignmentsService;
    @Autowired private FindAllAssignmentsService findAllAssignmentsService;
    @Autowired private DeleteAssignmentsService deleteAssignmentsService;
    @Autowired private UpdateAssignmentsService updateAssignmentsService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Page<AssignmentsResponse>> findAll(Pageable page, @PathVariable UUID id){
        Page<AssignmentsResponse> assignmentsResponse = findAllAssignmentsService.findAll(page, id);
        return ResponseEntity.ok().body(assignmentsResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AssignmentsResponse> save(@Valid @RequestBody AssignmentsSaveAddForm assignmentsSaveAddForm){
        AssignmentsResponse assignmentsResponse = saveAssignmentsService.addSave(assignmentsSaveAddForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentsResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody AssignmentsUpdateForm assignmentsUpdateForm){
        updateAssignmentsService.update(assignmentsUpdateForm, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteAssignmentsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
