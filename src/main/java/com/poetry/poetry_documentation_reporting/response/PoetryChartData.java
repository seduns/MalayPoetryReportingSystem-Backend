package com.poetry.poetry_documentation_reporting.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoetryChartData {
    private String title;
    private Number primaryMetric;   // Views or Donation Amount
    private Number secondaryMetric; // Likes (optional, for side-by-side bar)
}