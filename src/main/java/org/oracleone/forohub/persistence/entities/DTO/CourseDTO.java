package org.oracleone.forohub.persistence.entities.DTO;

import org.oracleone.forohub.Enums.Category;

public record CourseDTO(
        String name,
        Category category
) {
}
