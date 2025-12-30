package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoetryAnalyticsRepository extends JpaRepository<PoetryAnalytics, Long> {

    Optional<PoetryAnalytics> findByPoetryId(Long poetryId);

}
