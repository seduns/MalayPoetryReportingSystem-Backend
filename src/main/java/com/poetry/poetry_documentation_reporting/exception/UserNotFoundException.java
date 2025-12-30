package com.poetry.poetry_documentation_reporting.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AppException {
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
