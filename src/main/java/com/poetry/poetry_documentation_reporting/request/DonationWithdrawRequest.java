package com.poetry.poetry_documentation_reporting.request;

import lombok.Data;

@Data
public class DonationWithdrawRequest {
    private Long authorId;
    private double donationAmount;
}
