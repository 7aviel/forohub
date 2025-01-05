package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.entities.Topic;
import org.oracleone.forohub.persistence.entities.User;

import java.time.LocalDate;

public record AnswerDTO (
        @NotBlank
        String message,
        TopicDTO topicDTO,
        @NotBlank
        LocalDate creationDate,
        @NotBlank
        UserDTO author,
        String solution
) {
}
