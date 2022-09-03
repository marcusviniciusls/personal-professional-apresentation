package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.course.FindAllCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/course")
public class CourseController {

    @Autowired private FindAllCourseService findAllCourseService;

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> findAll(Pageable page){
        Page<CourseResponse> courseResponse = findAllCourseService.findAll(page);
        return ResponseEntity.ok().body(courseResponse);
    }
}
