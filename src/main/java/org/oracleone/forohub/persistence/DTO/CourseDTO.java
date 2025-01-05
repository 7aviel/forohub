package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.Enums.Category;
import org.oracleone.forohub.persistence.entities.Course;

public record CourseDTO(
        @NotNull
        String name,
        @NotNull
        Category category
) {
}
