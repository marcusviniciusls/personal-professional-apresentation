package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ActivitiesResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.FindAllActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/activities")
public class ActivitiesController {

    @Autowired private FindAllActivitiesService findAllActivitiesService;

    @GetMapping
    public ResponseEntity<List<ActivitiesResponse>> findAll(){
        List<ActivitiesResponse> listActivitiesResponse = findAllActivitiesService.findAll();
        return ResponseEntity.ok().body(listActivitiesResponse);
    }
}
