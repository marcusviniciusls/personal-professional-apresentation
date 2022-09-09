package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalGoalSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalGoalResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.DeleteProfessionalGoalService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.FindAllProfessionalGoalService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.FindByIdProfessionalGoalService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalgoal.SaveProfessionalGoalService;
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
@RequestMapping(value="/professionalgoal")
public class ProfessionalGoalController {

    @Autowired private FindAllProfessionalGoalService findAllProfessionalGoalService;
    @Autowired private FindByIdProfessionalGoalService findByIdProfessionalGoalService;
    @Autowired private SaveProfessionalGoalService saveProfessionalGoalService;
    @Autowired private DeleteProfessionalGoalService deleteProfessionalGoalService;

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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProfessionalGoalResponse> save(@Valid @RequestBody ProfessionalGoalSaveForm professionalGoalSaveForm){
        ProfessionalGoalResponse professionalGoalResponse = saveProfessionalGoalService.save(professionalGoalSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalGoalResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteProfessionalGoalService.delete(id);
        return ResponseEntity.ok().build();
    }
}
