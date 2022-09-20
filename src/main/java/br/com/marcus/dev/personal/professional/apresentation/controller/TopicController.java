package br.com.marcus.dev.personal.professional.apresentation.controller;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.TopicResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Topic;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.FindAllTopicService;
import br.com.marcus.dev.personal.professional.apresentation.services.topic.FindByIdTopicService;
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
@RequestMapping(value="/topic")
public class TopicController {

    @Autowired private FindAllTopicService findAllTopicService;
    @Autowired private FindByIdTopicService findByIdTopicService;

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
}
