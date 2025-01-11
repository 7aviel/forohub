package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.NotEmpty;

public record UserDetailsDTO (
        @NotEmpty
        String email,
        @NotEmpty
        String password
) {}
