package org.oracleone.forohub.service;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.oracleone.forohub.enums.Role;
import org.oracleone.forohub.persistence.DTO.UserDTO;
import org.oracleone.forohub.persistence.DTO.UserRegisterDTO;
import org.oracleone.forohub.persistence.repositories.UserRepository;
import org.oracleone.forohub.utils.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.oracleone.forohub.persistence.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService{

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void saveUser(UserRegisterDTO userRegisterDTO){
        this.userRepository.save(userRegisterDTOToUser(userRegisterDTO));
    }

    public User getUserById(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User userRegisterDTOToUser(UserRegisterDTO userRegisterDTO){
        return new User(
                userRegisterDTO.name(),
                userRegisterDTO.email(),
                passwordEncoder.encode(userRegisterDTO.password()),
                userRegisterDTO.role().stream()
                        .map(role -> Role.valueOf(role.name())).collect(Collectors.toSet()) // Convert enum values .collect(Collectors.toSet())
        );
    }

    public Page<UserDTO> getAllUsers(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        if (users.isEmpty()) {
            throw new EntityNotFoundException("No users found");
        }
        return users.map(
                this.userConverter::EntityToDTO
        );
    }

    public User getByNameAndEmail(String name, String email){
        return this.userRepository.findByNameAndEmail(name,email).orElseThrow(
                EntityNotFoundException::new
        );
    }

    public UserDTO convertToDTO(User user){
        return this.userConverter.EntityToDTO(user);
    }

}
