package org.oracleone.forohub.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.oracleone.forohub.Enums.Category;
import org.oracleone.forohub.persistence.DTO.CourseDTO;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;
    @NotBlank
    private String name;
    @Enumerated(value = EnumType.STRING)
    @NotNull
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
