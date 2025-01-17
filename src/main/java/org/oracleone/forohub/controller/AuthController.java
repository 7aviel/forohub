package org.oracleone.forohub.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.oracleone.forohub.persistence.DTO.AuthenticationRequest;
import org.oracleone.forohub.persistence.DTO.AuthenticationResponse;
import org.oracleone.forohub.persistence.DTO.UserRegisterDTO;
import org.oracleone.forohub.service.CustomUserDetailsService;
import org.oracleone.forohub.service.UserService;
import org.oracleone.forohub.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody
                                                       AuthenticationRequest authenticationRequest,
                                                       HttpSession session)  throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.username(),
                            authenticationRequest.password()) );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.username());
        final String jwt = jwtUtil.generateToken(userDetails);
        session.setAttribute("sessionDate", LocalDate.now());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> newUser(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        this.userService.saveUser(userRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

