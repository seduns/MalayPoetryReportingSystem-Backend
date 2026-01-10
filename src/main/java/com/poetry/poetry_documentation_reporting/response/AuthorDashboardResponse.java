package com.poetry.poetry_documentation_reporting.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class AuthorDashboardResponse {
    // 1. Cards Data
    private long totalPublishedPoetry;
    private long totalViews;
    private long totalLikes;
    private Double totalDonation;

    // 2. Chart Data
    private List<PoetryChartData> topViewedPoetry;   // For Bar Chart
    private List<PoetryChartData> topDonatedPoetry; // For Pie Chart
}