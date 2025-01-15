package org.oracleone.forohub.persistence.DTO.TopicDTOs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Status;
import org.oracleone.forohub.persistence.DTO.CourseDTO;

import java.time.LocalDate;

public record RegisterTopicDTO(
        @NotBlank
        String title,
        @NotNull
        LocalDate creationDate,
        @NotNull
        Status status,
        @NotNull
        @Valid
        CourseDTO course
) {
}
