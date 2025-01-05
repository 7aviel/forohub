package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull
        String name,
        @NotNull
        @Email
        String email

) {
}
