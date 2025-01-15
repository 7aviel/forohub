package org.oracleone.forohub.persistence.DTO;

import org.oracleone.forohub.enums.Category;

public record UpdateCourseDTO(
        String name,
        Category category
) {
}
