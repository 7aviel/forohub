package org.oracleone.forohub.persistence.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.oracleone.forohub.enums.Role;

import java.util.Set;

public record UserDTO(
        @NotNull
        String name,
        @NotNull
        @Email
        String email,
        @NotNull
        Set<Role> roles

) {
}
