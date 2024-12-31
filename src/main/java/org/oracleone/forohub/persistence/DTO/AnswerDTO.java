package org.oracleone.forohub.persistence.DTO;

import org.oracleone.forohub.persistence.entities.User;

import java.time.LocalDate;

public record AnswerDTO(
        String message,
        LocalDate creationDate,
        UserDTO author,
        String solution
) {
    public User userDTOtoUser(){
        return new User(author);
    }
}
