package org.oracleone.forohub.controller;
import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.UserRegisterDTO;
import org.oracleone.forohub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
