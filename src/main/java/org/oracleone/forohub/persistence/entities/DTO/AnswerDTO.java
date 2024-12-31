package org.oracleone.forohub.persistence.entities.DTO;

import java.time.LocalDate;

public record AnswerDTO(
        String message,
        LocalDate creationDate,
        UserDTO author,
        String solution
) {
}
