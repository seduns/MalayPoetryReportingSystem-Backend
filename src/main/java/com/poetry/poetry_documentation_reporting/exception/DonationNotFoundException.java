package com.poetry.poetry_documentation_reporting.exception;

import org.springframework.http.HttpStatus;

public class DonationNotFoundException extends AppException {
    public DonationNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

