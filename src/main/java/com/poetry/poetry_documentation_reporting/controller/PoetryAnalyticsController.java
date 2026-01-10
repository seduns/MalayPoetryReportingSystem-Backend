package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import com.poetry.poetry_documentation_reporting.response.AdminDashboardStatsResponse;
import com.poetry.poetry_documentation_reporting.response.AuthorDashboardResponse;
import com.poetry.poetry_documentation_reporting.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poetry/analytics")
public class PoetryAnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @PatchMapping("/{poetryId}/view")
    public ResponseEntity<Void> addView(@PathVariable Long poetryId) {
        analyticsService.addView(poetryId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{poetryId}/like")
    public ResponseEntity<Void> addLike(@PathVariable Long poetryId) {
        analyticsService.addLike(poetryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PoetryAnalytics>> getPoetryAnalyticsAll() throws Exception {
        List<PoetryAnalytics> analyticsList = analyticsService.getAllPoetryAnalytics();
        return new ResponseEntity<>(analyticsList, HttpStatus.OK);
    }

    @GetMapping("/{analyticId}")
    public ResponseEntity<PoetryAnalytics> getPoetryAnalytic(@PathVariable Long analyticId) throws Exception {
        PoetryAnalytics analytics = analyticsService.getPoetryAnalytics(analyticId);
        return new ResponseEntity<>(analytics, HttpStatus.OK);
    }

    @GetMapping("/dashboard/admin")
    public ResponseEntity<AdminDashboardStatsResponse> getDashboardStats() throws Exception {
        AdminDashboardStatsResponse stats = analyticsService.adminDashboard();
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/dashboard/author/{authorId}")
    public ResponseEntity<AuthorDashboardResponse> getDashboard(@PathVariable Long authorId) {
        return ResponseEntity.ok(analyticsService.authorDashbaord(authorId));
    }
}
