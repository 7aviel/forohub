package org.oracleone.forohub.persistence.DTO;

public record AuthenticationRequest(
        String username,
        String password
) {
}
