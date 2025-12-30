package com.poetry.poetry_documentation_reporting.exception;

import org.springframework.http.HttpStatus;

public class WithdrawDonationOutOfDonation extends AppException {
    public WithdrawDonationOutOfDonation(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

}
