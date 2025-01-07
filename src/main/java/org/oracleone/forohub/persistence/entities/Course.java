package org.oracleone.forohub.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.oracleone.forohub.enums.Category;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category categoryEnum;

    public Course(String name, Category categoryEnum) {
        this.name = name;
        this.categoryEnum = categoryEnum;
    }

    public String getName() {
        return name;
    }

    public Category getCategoryEnum() {
        return categoryEnum;
    }

    public Course() {
    }



}
