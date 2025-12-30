package com.poetry.poetry_documentation_reporting.request;

import com.poetry.poetry_documentation_reporting.model.enumoption.USER_ROLE;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private USER_ROLE role;
}