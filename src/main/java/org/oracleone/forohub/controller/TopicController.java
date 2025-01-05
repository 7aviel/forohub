package org.oracleone.forohub.controller;

import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.TopicDTO;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService.getById(id));
    }

}
