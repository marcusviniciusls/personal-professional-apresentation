package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.StudyPlanSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.StudyPlanResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.*;
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
@RequestMapping(value="/studyplan")
public class StudyPlanController {

    @Autowired private FindAllStudyPlanService findAllStudyPlanService;
    @Autowired private FindByIdEntityStudyPlanService findByIdStudyPlanService;
    @Autowired private SaveStudyPlanService saveStudyPlanService;
    @Autowired private DeleteStudyPlanService deleteStudyPlanService;

    @GetMapping
    public ResponseEntity<Page<StudyPlanResponse>> findById(Pageable pageable){
        Page<StudyPlanResponse> pageStudyPlan = findAllStudyPlanService.findAll(pageable);
        return ResponseEntity.ok().body(pageStudyPlan);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StudyPlanResponse> findById(@PathVariable UUID id){
        StudyPlanResponse studyPlanResponse = findByIdStudyPlanService.findById(id);
        return ResponseEntity.ok().body(studyPlanResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<StudyPlanResponse> save(@Valid @RequestBody StudyPlanSaveForm studyPlanSaveForm){
        StudyPlanResponse studyPlanResponse = saveStudyPlanService.save(studyPlanSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(studyPlanResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteStudyPlanService.delete(id);
        return ResponseEntity.ok().build();
    }
}
