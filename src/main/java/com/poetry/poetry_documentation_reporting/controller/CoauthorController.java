package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.model.Coauthor;
import com.poetry.poetry_documentation_reporting.response.AddCoauthorRequest;
import com.poetry.poetry_documentation_reporting.response.CoauthorResponse;
import com.poetry.poetry_documentation_reporting.service.CoauthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poetry")
@RequiredArgsConstructor
public class CoauthorController {

    private final CoauthorService coauthorService;


    @PostMapping("/{poetryId}/coauthors")
    public ResponseEntity<List<CoauthorResponse>> addCoauthors(@PathVariable Long poetryId, @RequestBody AddCoauthorRequest request) throws Exception {

        List<Coauthor> coauthors = coauthorService.addNewCoauthor(request.getCoauthors(), poetryId);

        List<CoauthorResponse> response = coauthors.stream()
                .map(c -> new CoauthorResponse(
                        c.getId(),
                        c.getAuthor().getPublicId(),
                        c.getAuthor().getUser().getFullName()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{poetryId}/coauthors")
    public ResponseEntity<List<CoauthorResponse>> getAllCoauthors(@PathVariable Long poetryId) throws Exception {

        List<CoauthorResponse> response = coauthorService.getAllCoauthor(poetryId)
                        .stream()
                        .map(c -> new CoauthorResponse(
                                c.getId(),
                                c.getAuthor().getPublicId(),
                                c.getAuthor().getUser().getFullName()
                        ))
                        .toList();

        return ResponseEntity.ok(response);
    }
}
