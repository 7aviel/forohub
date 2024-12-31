package org.oracleone.forohub.controller;

import org.oracleone.forohub.persistence.entities.DTO.TopicDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {

    @PostMapping("/topics")
    public ResponseEntity<?> postTopics(@RequestBody TopicDTO topicDTO){
        System.out.println(topicDTO.toString());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(topicDTO);
    }

}
