package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import com.poetry.poetry_documentation_reporting.response.AdminDashboardStatsResponse;
import com.poetry.poetry_documentation_reporting.response.AuthorDashboardResponse;

import java.util.List;

public interface AnalyticsService {

    public void addView(Long poetryId);
    public void addLike(Long poetryId);

    public List<PoetryAnalytics> getAllPoetryAnalytics() throws Exception;
    public PoetryAnalytics getPoetryAnalytics(Long analyticsId) throws Exception;
    public AdminDashboardStatsResponse adminDashboard() throws Exception;

    AuthorDashboardResponse authorDashbaord(long authorId);

}
