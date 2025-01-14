package org.oracleone.forohub.controller;
import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.UserDTO;
import org.oracleone.forohub.persistence.DTO.UserRegisterDTO;
import org.oracleone.forohub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        this.userService.saveUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userService.convertToDTO(this.userService.getUserById(id)));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userService.getAllUsers(pageable)
        );
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
