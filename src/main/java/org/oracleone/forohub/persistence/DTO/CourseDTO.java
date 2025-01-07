package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Category;

public record CourseDTO(
        @NotNull
        String name,
        @NotNull
        Category category
) {
}
