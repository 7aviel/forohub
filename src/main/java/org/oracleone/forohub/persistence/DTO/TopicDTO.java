package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.oracleone.forohub.Enums.Status;
import org.oracleone.forohub.persistence.entities.Answer;
import org.oracleone.forohub.persistence.entities.Course;
import org.oracleone.forohub.persistence.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record TopicDTO(
        @NotBlank
        String title,
        @NotBlank
        LocalDate creationDate,
        @NotBlank
        Status status,
        @NotBlank
        @Valid
        UserDTO user,
        @NotBlank
        @Valid
        CourseDTO course,
        List<AnswerDTO> answers
) {
}
