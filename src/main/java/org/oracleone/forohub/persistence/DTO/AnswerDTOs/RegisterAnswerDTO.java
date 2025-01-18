package org.oracleone.forohub.persistence.DTO.AnswerDTOs;

import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Solution;

public record RegisterAnswerDTO(
        @NotNull
        String message,
        @NotNull
        Long topicId,
        Solution solution
) {
}
