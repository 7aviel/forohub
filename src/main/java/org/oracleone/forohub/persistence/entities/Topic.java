package org.oracleone.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.oracleone.forohub.Enums.Status;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long id;
    private String title;
    private LocalDate creationDate;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

}
