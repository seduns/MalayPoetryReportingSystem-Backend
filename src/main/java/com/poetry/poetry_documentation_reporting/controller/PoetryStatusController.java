package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.repository.PoetryStatusRepository;
import com.poetry.poetry_documentation_reporting.response.PoetryStatusResponse;
import com.poetry.poetry_documentation_reporting.service.PoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/poetry/status")
public class PoetryStatusController {

    @Autowired
    private PoetryService poetryService;

    @PutMapping("/{poetryId}")
    public ResponseEntity<PoetryStatusResponse> updatePoetryStatus(@PathVariable Long poetryId, @RequestParam String status) throws Exception {

        return ResponseEntity.ok(poetryService.updatePoetryStatus(poetryId, status));
    }


}

