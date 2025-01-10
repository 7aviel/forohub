package org.oracleone.forohub.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.oracleone.forohub.persistence.DTO.TopicDTO;
import org.oracleone.forohub.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<?> postTopics(@RequestBody @Valid TopicDTO topicDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService
                .createNewTopic(topicDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable @NotBlank Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService.convertTopicToDTO(
                topicService.getById(id)
        ));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDTO>> getAllTopics(@PageableDefault(size = 8,sort = {"creationDate"}) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService.getAllTopics(pageable));
    }

}
