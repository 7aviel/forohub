package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        String name,
        @NotBlank
        String email

) {
}
