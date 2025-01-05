package org.oracleone.forohub.persistence.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oracleone.forohub.Enums.Status;
import org.oracleone.forohub.persistence.DTO.TopicDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private LocalDate creationDate;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotBlank
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotBlank
    private Course course;
    @OneToMany(mappedBy = "topic",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Answer> answers;

    public Topic(String title, LocalDate creationDate, Status status, User user, Course course, List<Answer> answers) {
        this.title = title;
        this.creationDate = creationDate;
        this.status = status;
        this.user = user;
        this.course = course;
        this.answers = answers;
    }

    public Topic() {
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
