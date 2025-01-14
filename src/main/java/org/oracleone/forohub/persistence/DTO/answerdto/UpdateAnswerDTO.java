package org.oracleone.forohub.persistence.DTO.answerdto;

import jakarta.validation.constraints.NotEmpty;
import org.oracleone.forohub.enums.Solution;

public record UpdateAnswerDTO(
        @NotEmpty
        String message,
        Solution solution
) {
}
