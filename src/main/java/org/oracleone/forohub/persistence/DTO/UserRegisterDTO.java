package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterDTO (
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}