package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.FindAllBranchActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/branchactivity")
public class BranchActivityController {

    @Autowired private FindAllBranchActivityService findAllBranchActivityService;

    @GetMapping
    public ResponseEntity<Page<BranchActivityResponse>> findAllBranchActivity(Pageable page){
        Page<BranchActivityResponse> branchActivityResponse = findAllBranchActivityService.findAll(page);
        return ResponseEntity.ok().body(branchActivityResponse);
    }
}
