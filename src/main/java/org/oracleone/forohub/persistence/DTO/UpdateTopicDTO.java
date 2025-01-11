package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotEmpty;
import org.oracleone.forohub.enums.Status;

import java.util.List;

public record UpdateTopicDTO(
        String title,
        Status status,
        List<AnswerDTO> answers
) {
}
