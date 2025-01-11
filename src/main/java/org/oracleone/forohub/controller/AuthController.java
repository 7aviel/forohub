package org.oracleone.forohub.controller;

import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.UserDetailsDTO;
import org.oracleone.forohub.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<HttpStatus> authUser(@RequestBody @Valid UserDetailsDTO userDetailsDTO){
        this.authService.authUser(userDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
