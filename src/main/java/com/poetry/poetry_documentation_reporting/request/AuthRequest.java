package com.poetry.poetry_documentation_reporting.request;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
