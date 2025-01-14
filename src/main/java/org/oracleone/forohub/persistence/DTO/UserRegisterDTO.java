package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Role;

import java.util.Set;

public record UserRegisterDTO (
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @NotNull
        Set<Role> role
) {
}
