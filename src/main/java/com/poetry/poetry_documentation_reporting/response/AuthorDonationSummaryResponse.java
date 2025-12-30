package com.poetry.poetry_documentation_reporting.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDonationSummaryResponse {

    private Long authorId;
    private Double totalDonation;
    private Double currentDonationBalance;

}
