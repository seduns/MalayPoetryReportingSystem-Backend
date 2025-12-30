package com.poetry.poetry_documentation_reporting.response;

import lombok.Data;

@Data
public class DonationWithdrawResponse {
    private String message;
    private double donationAmount;
}
