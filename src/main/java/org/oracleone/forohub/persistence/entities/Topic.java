package org.oracleone.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.oracleone.forohub.Enums.Status;
import org.oracleone.forohub.persistence.DTO.TopicDTO;

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
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(mappedBy = "topic",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Answer> answers;

    public Topic(TopicDTO topicDTO) {
        this.title = topicDTO.title();
        this.creationDate = topicDTO.creationDate();
        this.status = topicDTO.status();
        this.user = topicDTO.userDTOtoUser();
        this.course = topicDTO.courseDTOtoCourse();
        this.answers = topicDTO.answerDTOtoAnswer();
    }
}
