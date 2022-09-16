package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormOnlySave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.SubjectFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.SubjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.services.subject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value="/subject")
public class SubjectController {

    @Autowired private SaveSubjectService saveSubjectService;
    @Autowired private UpdateSubjectService updateSubjectService;
    @Autowired private DeleteSubjectService deleteSubjectService;
    @Autowired private SaveImageSubjectService saveImageSubjectService;
    @Autowired private DeleteImageSubjectService deleteImageSubjectService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<SubjectResponse> save(@Valid @RequestBody SubjectFormOnlySave subjectFormOnlySave){
        SubjectResponse subjectResponse = saveSubjectService.save(subjectFormOnlySave);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/{id}/image")
    public ResponseEntity<?> saveImage(@RequestParam(name = "file") MultipartFile multipartFile, @PathVariable UUID id){
        saveImageSubjectService.saveimage(multipartFile, id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<SubjectResponse> update(@PathVariable UUID id, @Valid @RequestBody SubjectFormUpdate subjectFormUpdate){
        SubjectResponse subjectResponse = updateSubjectService.update(subjectFormUpdate, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(subjectResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteSubjectService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}/image")
    public ResponseEntity<?> deleteImage(@PathVariable UUID id){
        deleteImageSubjectService.deleteImageS3(id);
        return ResponseEntity.ok().build();
    }
}
