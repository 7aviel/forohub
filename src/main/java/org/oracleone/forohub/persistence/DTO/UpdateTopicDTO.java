package org.oracleone.forohub.persistence.DTO;

import org.oracleone.forohub.enums.Status;
import org.oracleone.forohub.persistence.DTO.answerdto.AnswerDTO;

import java.util.List;

public record UpdateTopicDTO(
        String title,
        Status status,
        List<AnswerDTO> answers
) {
}
