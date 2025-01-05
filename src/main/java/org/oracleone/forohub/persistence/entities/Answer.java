package org.oracleone.forohub.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @JoinColumn(name = "topic_id")
    @JsonBackReference
    private Topic topic;
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;
    private String solution;

    public Answer() {

    }

    public Answer(String message, Topic topic, LocalDate creationDate, User author, String solution) {
        this.message = message;
        this.topic = topic;
        this.creationDate = creationDate;
        this.author = author;
        this.solution = solution;
    }

    public String getMessage() {
        return message;
    }

    public Topic getTopic() {
        return topic;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public String getSolution() {
        return solution;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
