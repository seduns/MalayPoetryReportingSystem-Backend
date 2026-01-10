package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import com.poetry.poetry_documentation_reporting.response.PoetryChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
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

    // Card: Total Views for Author
    @Query("SELECT COALESCE(SUM(pa.viewCount), 0) FROM PoetryAnalytics pa WHERE pa.poetry.author.user.id = :userId")
    long sumViewsByAuthor(@Param("userId") Long userId);

    // Card: Total Likes for Author
    @Query("SELECT COALESCE(SUM(pa.likeCount), 0) FROM PoetryAnalytics pa WHERE pa.poetry.author.user.id = :userId")
    long sumLikesByAuthor(@Param("userId") Long userId);

    // Chart 1: Top 5 High View (Returns Title, Views, Likes)
// ✅ FIXED: Removed ".dto" from the package path below
    @Query("SELECT new com.poetry.poetry_documentation_reporting.response.PoetryChartData(" +
            "pa.poetry.title, CAST(pa.viewCount AS double), pa.likeCount) " +
            "FROM PoetryAnalytics pa " +
            "WHERE pa.poetry.author.user.id = :userId " +
            "ORDER BY pa.viewCount DESC")
    List<PoetryChartData> findTopViewedPoetryByAuthor(@Param("userId") Long userId, Pageable pageable);

}
