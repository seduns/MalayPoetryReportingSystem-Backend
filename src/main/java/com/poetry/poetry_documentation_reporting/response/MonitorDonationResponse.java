package com.poetry.poetry_documentation_reporting.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonitorDonationResponse {

    private String poetryTitle;
    private String poetryOwner;
    private int donationCount;
    private double donationAmount;


}
