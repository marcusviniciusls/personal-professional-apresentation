package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormOnlySave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SubjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.subject.SaveSubjectService;
import br.com.marcus.dev.personal.professional.apresentation.services.subject.UpdateSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value="/subject")
public class SubjectController {

    @Autowired private SaveSubjectService saveSubjectService;
    @Autowired private UpdateSubjectService updateSubjectService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SubjectResponse> save(@Valid @RequestBody SubjectFormOnlySave subjectFormOnlySave){
        SubjectResponse subjectResponse = saveSubjectService.save(subjectFormOnlySave);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SubjectResponse> update(@PathVariable UUID id, @Valid @RequestBody SubjectFormUpdate subjectFormUpdate){
        SubjectResponse subjectResponse = updateSubjectService.update(subjectFormUpdate, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(subjectResponse);
    }
}
