package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoetryAnalyticsRepository extends JpaRepository<PoetryAnalytics, Long> {

    Optional<PoetryAnalytics> findByPoetryId(Long poetryId);

    // Sum of all 'views' column in the Analytics table
    @Query("SELECT COALESCE(SUM(pa.viewCount), 0) FROM PoetryAnalytics pa")
    long getTotalSystemViews();

    // ✅ FIXED: Changed 'likes' to 'likeCount' (Matches your Entity)
    @Query("SELECT COALESCE(SUM(pa.likeCount), 0) FROM PoetryAnalytics pa")
    long getTotalSystemLikes();

}
