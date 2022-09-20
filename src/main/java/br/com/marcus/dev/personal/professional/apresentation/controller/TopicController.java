package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.TelephoneFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.TopicSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.DeleteTopicService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.FindAllTopicService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.FindByIdTopicService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.SaveTopicService;
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
@RequestMapping(value="/topic")
public class TopicController {

    @Autowired private FindAllTopicService findAllTopicService;
    @Autowired private FindByIdTopicService findByIdTopicService;
    @Autowired private SaveTopicService saveTopicService;
    @Autowired private DeleteTopicService deleteTopicService;

    @GetMapping(value = "/studyplan/{id}")
    public ResponseEntity<Page<TopicResponse>> findById(Pageable pageable, @PathVariable UUID id){
        Page<TopicResponse> pageTopicResponse = findAllTopicService.findAll(pageable, id);
        return ResponseEntity.ok().body(pageTopicResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicResponse> findById(@PathVariable UUID id){
        TopicResponse topicResponse = findByIdTopicService.findById(id);
        return ResponseEntity.ok().body(topicResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TopicResponse> save(@Valid @RequestBody TopicSaveForm topicSaveForm){
        TopicResponse topicResponse = saveTopicService.save(topicSaveForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicResponse);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        deleteTopicService.delete(id);
        return ResponseEntity.ok().build();
    }
}
