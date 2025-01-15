package org.oracleone.forohub.persistence.DTO;

import org.oracleone.forohub.enums.Role;

import java.util.Set;

public record UserRoleDTO(
        Set<Role> roles
) {
}
