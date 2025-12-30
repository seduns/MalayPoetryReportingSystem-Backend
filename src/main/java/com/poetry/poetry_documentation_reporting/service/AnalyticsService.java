package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Poetry;

public interface AnalyticsService {

    public void addView(Long poetryId);
    public void addLike(Long poetryId);


}
