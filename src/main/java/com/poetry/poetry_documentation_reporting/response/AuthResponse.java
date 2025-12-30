package com.poetry.poetry_documentation_reporting.response;

import com.poetry.poetry_documentation_reporting.model.enumoption.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

    private String accessToken;
    private String message;
    private String status;
    private String refreshToken;
    private USER_ROLE role;

}
