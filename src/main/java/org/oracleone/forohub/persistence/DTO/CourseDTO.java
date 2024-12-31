package org.oracleone.forohub.persistence.DTO;

import org.oracleone.forohub.Enums.Category;
import org.oracleone.forohub.persistence.entities.Course;

public record CourseDTO(
        String name,
        Category category
) {
}
