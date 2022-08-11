package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.BranchActivityForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.FindAllBranchActivityService;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.FindByIdBranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.SaveBranchActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value="/branchactivity")
public class BranchActivityController {

    @Autowired private FindAllBranchActivityService findAllBranchActivityService;
    @Autowired private FindByIdBranchActivity findByIdBranchActivity;
    @Autowired private SaveBranchActivityService saveBranchActivityService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<BranchActivityResponse>> findAllBranchActivity(Pageable page){
        Page<BranchActivityResponse> branchActivityResponse = findAllBranchActivityService.findAll(page);
        return ResponseEntity.ok().body(branchActivityResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<BranchActivityResponse> findById(@PathVariable UUID id){
        BranchActivityResponse branchActivity = findByIdBranchActivity.findById(id);
        return ResponseEntity.ok().body(branchActivity);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BranchActivityResponse> save(@RequestBody BranchActivityForm branchActivityForm){
        BranchActivityResponse branchActivityResponse = saveBranchActivityService.save(branchActivityForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(branchActivityResponse);
    }
}
