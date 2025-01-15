package org.oracleone.forohub.controller;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.persistence.DTO.TopicDTOs.RegisterTopicDTO;
import org.oracleone.forohub.persistence.DTO.TopicDTOs.TopicDTO;
import org.oracleone.forohub.persistence.DTO.UpdateTopicDTO;
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
    public ResponseEntity<?> postTopics(@RequestBody @Valid RegisterTopicDTO registerTopicDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService
                .createNewTopic(registerTopicDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopicById(@PathVariable @NotNull Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService.convertTopicToDTO(
                topicService.getById(id)
        ));
    }

    @GetMapping
    public ResponseEntity<Page<TopicDTO>> getAllTopics(@PageableDefault(size = 10,sort = {"creationDate"}) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService.getAllTopics(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopicById(@PathVariable @NotNull Long id){
        this.topicService.deleteTopic(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTopic(@RequestBody @Valid UpdateTopicDTO updateTopicDTO,
                                         @PathVariable @NotNull Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.topicService.updateTopic(updateTopicDTO, id));
    }

}
