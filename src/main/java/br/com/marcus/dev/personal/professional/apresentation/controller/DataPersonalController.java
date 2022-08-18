package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.DataPersonalFullFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.DataPersonalDto;
import br.com.marcus.dev.personal.professional.apresentation.services.datapersonal.*;
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
@RequestMapping(value="/datapersonal")
public class DataPersonalController {

    @Autowired private FindAllDataPersonalService findAllDataPersonalService;
    @Autowired private FindByIdDataPersonalService findByIdDataPersonalService;
    @Autowired private SaveDataPersonalService saveDataPersonalService;
    @Autowired private DeleteDataPersonalService deleteDataPersonalService;
    @Autowired private UpdateDataPersonalService updateDataPersonalService;

    @GetMapping
    public ResponseEntity<Page<DataPersonalDto>> findAll(Pageable page){
        Page<DataPersonalDto> dataPersonalDto = findAllDataPersonalService.findAll(page);
        return ResponseEntity.ok().body(dataPersonalDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DataPersonalDto> findById(@PathVariable UUID id){
        DataPersonalDto dataPersonalDto = findByIdDataPersonalService.findById(id);
        return ResponseEntity.ok().body(dataPersonalDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DataPersonalDto> save(@Valid @RequestBody DataPersonalFullForm dataPersonalFullForm){
        DataPersonalDto dataPersonalDto = saveDataPersonalService.save(dataPersonalFullForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(dataPersonalDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<DataPersonalDto> update(@PathVariable UUID id, @Valid @RequestBody DataPersonalFullFormUpdate dataPersonalFullFormUpdate){
        DataPersonalDto dataPersonalDto = updateDataPersonalService.update(dataPersonalFullFormUpdate, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(dataPersonalDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteDataPersonalService.delete(id);
        return ResponseEntity.ok().build();
    }
}
