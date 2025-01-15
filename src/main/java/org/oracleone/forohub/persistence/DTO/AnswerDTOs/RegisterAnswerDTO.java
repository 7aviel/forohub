package org.oracleone.forohub.persistence.DTO.AnswerDTOs;

import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Solution;
import org.oracleone.forohub.persistence.DTO.UserDTO;

import java.time.LocalDate;

public record RegisterAnswerDTO(
        @NotNull
        String message,
        Long topicId,
        @NotNull
        LocalDate creationDate,
        Solution solution
) {
}
