package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.FindAllDataPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/datapersonal")
public class DataPersonalController {

    @Autowired private FindAllDataPersonalService findAllDataPersonalService;

    @GetMapping
    public ResponseEntity<Page<DataPersonalDto>> findAll(Pageable page){
        Page<DataPersonalDto> dataPersonalDto = findAllDataPersonalService.findAll(page);
        return ResponseEntity.ok().body(dataPersonalDto);
    }
}
