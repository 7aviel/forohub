package org.oracleone.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.oracleone.forohub.persistence.DTO.AnswerDTO;

import java.time.LocalDate;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    private String solution;

    public Answer(AnswerDTO answerDTO) {
        this.message = answerDTO.message();
        this.creationDate = answerDTO.creationDate();
        this.author = answerDTO.userDTOtoUser();
        this.solution = answerDTO.solution();
    }
}
