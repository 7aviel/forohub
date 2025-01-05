package org.oracleone.forohub.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.oracleone.forohub.interfaces.EntityConverter;
import org.oracleone.forohub.persistence.DTO.UserDTO;
import org.oracleone.forohub.persistence.DTO.UserRegisterDTO;
import org.oracleone.forohub.persistence.entities.Course;
import org.oracleone.forohub.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.oracleone.forohub.persistence.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements EntityConverter<UserDTO, User>{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(UserRegisterDTO userRegisterDTO){
        return this.userRepository.save(userRegisterDTOToUser(userRegisterDTO));
    }

    public User getUserById(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User userRegisterDTOToUser(UserRegisterDTO userRegisterDTO){
        return new User(
                userRegisterDTO.name(),
                userRegisterDTO.email(),
                userRegisterDTO.password()
        );
    }


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

    public List<UserDTO> getAllUsers(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found");
        }
        return users.stream().map(
                this::EntityToDTO
        ).collect(Collectors.toList());
    }

    public User getByNameAndEmail(String name, String email){
        return this.userRepository.findByNameAndEmail(name,email).orElseThrow(
                EntityNotFoundException::new
        );
    }

}
