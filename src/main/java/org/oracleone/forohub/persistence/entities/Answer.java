package org.oracleone.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
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
    private Topic topic;
    private LocalDate creationDate;
    @ManyToOne
    private User author;
    private String solution;

}
