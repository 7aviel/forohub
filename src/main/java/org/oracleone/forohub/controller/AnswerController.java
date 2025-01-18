package org.oracleone.forohub.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.AnswerDTOs.AnswerDTO;
import org.oracleone.forohub.persistence.DTO.AnswerDTOs.RegisterAnswerDTO;
import org.oracleone.forohub.persistence.DTO.AnswerDTOs.UpdateAnswerDTO;
import org.oracleone.forohub.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<?> saveAnswer(@RequestBody @Valid RegisterAnswerDTO registerAnswerDTO,
                                        HttpSession session){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.answerService
                .saveNewAnswer(registerAnswerDTO, session));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.answerService.convertToDTO(
                this.answerService.findById(id)
        ));
    }

    @GetMapping
    public ResponseEntity<Page<AnswerDTO>> getAllAnswers(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.answerService.getAllAnswers(pageable)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id){
        this.answerService.deleteAnswer(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnswer(@RequestBody @Valid UpdateAnswerDTO updateAnswerDTO,
                                          @PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.answerService.updateAnswer(updateAnswerDTO, id));
    }
}
