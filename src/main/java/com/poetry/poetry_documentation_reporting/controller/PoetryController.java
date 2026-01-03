package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.exception.PoetryNotFoundException;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.request.PoetryRequest;
import com.poetry.poetry_documentation_reporting.response.StatusResponse;
import com.poetry.poetry_documentation_reporting.service.PoetryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/poetry")
public class PoetryController {

    @Autowired
    private PoetryService poetryService;

    @PostMapping("/create/{authorId}")
    public ResponseEntity<Poetry> createPoetry(@PathVariable Long authorId, @Valid @RequestBody PoetryRequest request) throws Exception {

        Poetry poetry = poetryService.createPoetry(request, authorId);

        return new ResponseEntity<>(poetry, HttpStatus.CREATED);
    }

    @GetMapping("/{poetryId}")
    public ResponseEntity<Poetry> getPoetry(@PathVariable Long poetryId) throws Exception {
        Poetry poetry = poetryService.getPoetry(poetryId);
        return new ResponseEntity<>(poetry, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Poetry>> getAllPoetry() throws Exception {
        List<Poetry> allPoetry = poetryService.getAllPoetry();
        return new ResponseEntity<>(allPoetry, HttpStatus.OK);
    }


    @PutMapping("/edit/{poetryId}")
    public ResponseEntity<Poetry> updatePoetry(@PathVariable Long poetryId, @RequestBody @Valid PoetryRequest request) throws Exception  {
        Poetry updated = poetryService.updatePoetry(poetryId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{poetryId}")
    public ResponseEntity<StatusResponse> deletePoetry(@PathVariable Long poetryId) throws Exception  {
        String status = poetryService.deletePoetry(poetryId);
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setStatus(status);

        return new ResponseEntity<>(statusResponse, HttpStatus.OK);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Poetry>> getPoetryByAuthor(@PathVariable Long authorId) throws Exception {
        List<Poetry> poetryList = poetryService.getPoetryByAuthorId(authorId);
        return ResponseEntity.ok(poetryList);
    }
}
