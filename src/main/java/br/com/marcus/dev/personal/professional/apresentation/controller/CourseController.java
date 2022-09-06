package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.services.course.*;
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
@RequestMapping(value="/course")
public class CourseController {

    @Autowired private FindAllCourseService findAllCourseService;
    @Autowired private FindByIdCourseService findByIdCourseService;
    @Autowired private SaveCourseService saveCourseService;
    @Autowired private DeleteCourseService deleteCourseService;
    @Autowired private UpdateCourseService updateCourseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> findAll(Pageable page){
        Page<CourseResponse> courseResponse = findAllCourseService.findAll(page);
        return ResponseEntity.ok().body(courseResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseResponse> findById(@PathVariable UUID id){
        CourseResponse courseResponse = findByIdCourseService.findById(id);
        return ResponseEntity.ok().body(courseResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<CourseResponse> save(@Valid @RequestBody CourseSaveForm courseSaveForm){
        CourseResponse courseResponse = saveCourseService.save(courseSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CourseResponse> update(@PathVariable UUID id, @Valid @RequestBody CourseUpdateForm courseUpdateForm){
        CourseResponse courseResponse = updateCourseService.update(courseUpdateForm, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteCourseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
