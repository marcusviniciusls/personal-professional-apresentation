package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.StudyPlanResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.FindAllStudyPlanService;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.FindByIdEntityStudyPlanService;
import br.com.marcus.dev.personal.professional.apresentation.services.studyplan.FindByIdStudyPlanService;
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
@RequestMapping(value="/studyplan")
public class StudyPlanController {

    @Autowired private FindAllStudyPlanService findAllStudyPlanService;
    @Autowired private FindByIdEntityStudyPlanService findByIdStudyPlanService;

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
}
