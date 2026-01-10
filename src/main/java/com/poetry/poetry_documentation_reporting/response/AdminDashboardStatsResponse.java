package com.poetry.poetry_documentation_reporting.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDashboardStatsResponse {
    private long totalUser;
    private long totalAuthor;
    private long totalViews;
    private long totalLikes;
    private long totalPoetry;
    private long totalDonationCount;
    private Double totalDonationAmount;
    private long totalApprovedPoetry;

}