package org.oracleone.forohub.persistence.entities.DTO;

import org.oracleone.forohub.Enums.Status;

import java.time.LocalDate;
import java.util.List;

public record TopicDTO(
        String title,
        LocalDate creationDate,
        Status status,
        UserDTO user,
        CourseDTO course,
        List<AnswerDTO> answers
) {
}
