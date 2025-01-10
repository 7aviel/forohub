package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Solution;

import java.time.LocalDate;

public record AnswerDTO (
        @NotNull
        String message,
        String topic,
        @NotNull
        LocalDate creationDate,
        @NotNull
        UserDTO author,
        Solution solution
) {
}
