package org.oracleone.forohub.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.oracleone.forohub.enums.Solution;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;
    @NotNull
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    @JsonBackReference
    private Topic topic;
    @NotNull
    private LocalDate creationDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User author;
    @Enumerated(EnumType.STRING)
    private Solution solution;

    public Answer(String message, Topic topic, LocalDate localDate, User user, Solution solution)
    {
        this.message = message;
        this.topic = topic;
        this.creationDate = localDate;
        this.author = user;
        this.solution = solution;
    }

    public Answer() {
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

    public void setSolution(Solution solution) {
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

    public Solution getSolution() {
        return solution;
    }
}
