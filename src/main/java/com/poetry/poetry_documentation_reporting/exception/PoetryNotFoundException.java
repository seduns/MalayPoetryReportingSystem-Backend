package com.poetry.poetry_documentation_reporting.exception;

import org.springframework.http.HttpStatus;

public class PoetryNotFoundException extends AppException {
    public PoetryNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
