package org.oracleone.forohub.utils;

import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.UserDTO;
import org.oracleone.forohub.persistence.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements EntityConverter<UserDTO, User> {

    @Override
    public UserDTO EntityToDTO(User user) {
        return new UserDTO(user.getName(),user.getEmail());
    }

    @Override
    public User DTOtoEntity(UserDTO userDTO) {
        return new User(
                userDTO.name(),
                userDTO.email()
        );
    }
}
