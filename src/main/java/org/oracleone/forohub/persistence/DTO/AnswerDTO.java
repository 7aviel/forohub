package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AnswerDTO (
        @NotNull
        String message,
        TopicDTO topic,
        @NotNull
        LocalDate creationDate,
        @NotNull
        UserDTO author,
        String solution
) {
}
