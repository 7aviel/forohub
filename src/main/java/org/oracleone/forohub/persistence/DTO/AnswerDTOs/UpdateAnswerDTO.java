package org.oracleone.forohub.persistence.DTO.AnswerDTOs;
import org.oracleone.forohub.enums.Solution;

public record UpdateAnswerDTO(
        String message,
        Solution solution
) {
}
