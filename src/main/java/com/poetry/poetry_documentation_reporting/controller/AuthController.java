package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.config.JwtProvider;
import com.poetry.poetry_documentation_reporting.exception.EmailAlreadyExistsException;
import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.repository.AuthRepository;
import com.poetry.poetry_documentation_reporting.request.AuthRequest;
import com.poetry.poetry_documentation_reporting.request.SignupRequest;
import com.poetry.poetry_documentation_reporting.response.AuthResponse;
import com.poetry.poetry_documentation_reporting.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createNewUser(@Valid @RequestBody SignupRequest user) throws Exception {

        AuthResponse authResponse = authService.registerNewUser(user);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody AuthRequest user) throws Exception {

        AuthResponse authResponse = authService.loginTheUSer(user);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }



}
