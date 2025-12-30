package com.poetry.poetry_documentation_reporting.exception;

import org.springframework.http.HttpStatus;

public class PoetryAnalyticsNotFoundException extends AppException {
    public PoetryAnalyticsNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

