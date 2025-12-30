package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
