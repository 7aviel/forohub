package org.oracleone.forohub.controller;

import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.AnswerDTO;
import org.oracleone.forohub.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<?> saveAnswer(@RequestBody @Valid AnswerDTO answerDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.answerService.saveNewAnswer(answerDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.answerService.convertToDTO(
                this.answerService.findById(id)
        ));
    }

    @GetMapping
    public ResponseEntity<List<AnswerDTO>> getAllAnswers(){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.answerService.getAllAnswers()
        );
    }
}
