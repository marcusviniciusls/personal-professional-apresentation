package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveAddForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CertificateSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CertificateResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.SaveAssignmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/assignments")
public class AssignmentsController {

    @Autowired private SaveAssignmentsService saveAssignmentsService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AssignmentsResponse> save(@Valid @RequestBody AssignmentsSaveAddForm assignmentsSaveAddForm){
        AssignmentsResponse assignmentsResponse = saveAssignmentsService.addSave(assignmentsSaveAddForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentsResponse);
    }
}
