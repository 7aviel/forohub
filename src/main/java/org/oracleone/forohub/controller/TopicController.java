package org.oracleone.forohub.controller;

import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.TopicDTO;
import org.oracleone.forohub.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/topics")
    public ResponseEntity<?> postTopics(@RequestBody @Valid TopicDTO topicDTO){
       /* System.out.println(topicDTO.toString());
        this.topicService.saveTopic(topicDTO);*/
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
