package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.request.AuthRequest;
import com.poetry.poetry_documentation_reporting.request.SignupRequest;
import com.poetry.poetry_documentation_reporting.response.AuthResponse;

import java.util.Optional;

public interface AuthService {
    public Optional<User> findUserByEmail(String email) throws Exception;

    public AuthResponse registerNewUser(SignupRequest user) throws Exception;
    public AuthResponse loginTheUSer(AuthRequest authRequest) throws Exception;

}
