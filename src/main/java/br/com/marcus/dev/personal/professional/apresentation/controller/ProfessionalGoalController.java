package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.FindAllProfessionalGoalService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.FindByIdProfessionalGoalService;
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
@RequestMapping(value="/professionalgoal")
public class ProfessionalGoalController {

    @Autowired private FindAllProfessionalGoalService findAllProfessionalGoalService;
    @Autowired private FindByIdProfessionalGoalService findByIdProfessionalGoalService;

    @GetMapping
    public ResponseEntity<Page<ProfessionalGoalResponse>> findAll(Pageable page){
        Page<ProfessionalGoalResponse> pageProfessionalGoal = findAllProfessionalGoalService.findAll(page);
        return ResponseEntity.ok().body(pageProfessionalGoal);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfessionalGoalResponse> findById(@PathVariable UUID id){
        ProfessionalGoalResponse professionalGoalResponse = findByIdProfessionalGoalService.findById(id);
        return ResponseEntity.ok().body(professionalGoalResponse);
    }
}
