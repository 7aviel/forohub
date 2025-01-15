package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDetailsDTO (
        @NotEmpty
        String name,
        @NotNull
        String email,
        @NotNull
        String password
) {}
