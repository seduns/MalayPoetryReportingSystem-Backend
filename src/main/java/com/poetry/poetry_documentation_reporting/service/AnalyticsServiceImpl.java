package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.exception.PoetryAnalyticsNotFoundException;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import com.poetry.poetry_documentation_reporting.repository.PoetryAnalyticsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private PoetryAnalyticsRepository poetryAnalyticsRepository;

    @Autowired
    private PoetryAnalyticsRepository analyticsRepository;

    @Override
    @Transactional
    public void addView(Long poetryId) {

        PoetryAnalytics analytics = analyticsRepository.findByPoetryId(poetryId)
                .orElseThrow(() -> new PoetryAnalyticsNotFoundException("analytics_not_found"));

        analytics.setViewCount(analytics.getViewCount() + 1);
        // No need save() if inside @Transactional
    }

    @Override
    @Transactional
    public void addLike(Long poetryId) {

        PoetryAnalytics analytics = analyticsRepository.findByPoetryId(poetryId)
                .orElseThrow(() -> new PoetryAnalyticsNotFoundException("analytics_not_found"));

        analytics.setLikeCount(analytics.getLikeCount() + 1);
        // No need save() if inside @Transactional
    }
}
