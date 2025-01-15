package org.oracleone.forohub.persistence.DTO.UserDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserDTOTopic(
        @NotNull
        String name,
        @NotNull
        @Email
        String email
) {
}
