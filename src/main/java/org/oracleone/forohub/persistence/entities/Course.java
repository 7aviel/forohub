package org.oracleone.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.oracleone.forohub.Enums.Category;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category categoryEnum;
}
