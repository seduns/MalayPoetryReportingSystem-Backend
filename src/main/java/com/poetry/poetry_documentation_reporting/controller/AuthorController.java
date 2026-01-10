package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileRequest;
import com.poetry.poetry_documentation_reporting.response.AuthorResponse;
import com.poetry.poetry_documentation_reporting.response.UserResponse;
import com.poetry.poetry_documentation_reporting.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/admin/all")
    public ResponseEntity<List<Author>> getAllAuthor() throws Exception {
        List<Author> authorList = authorService.getAllAuthor();
        return ResponseEntity.ok(authorList);
    }

    @PutMapping("/{authorId}/profile")
    public ResponseEntity<AuthorResponse> updateProfile(@PathVariable Long authorId, @RequestBody UpdateProfileRequest request) throws Exception {

        Author updatedAuthor = authorService.updateAuthorProfile(authorId, request);

        AuthorResponse response = new AuthorResponse(
                updatedAuthor.getId(),
                updatedAuthor.getBio(),
                updatedAuthor.getUser().getEmail(),
                updatedAuthor.getUser().getFullName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{authorId}/profile")
    public ResponseEntity<Author> getAuthorProfile(@PathVariable Long authorId) throws Exception {
        Author author = authorService.getAuthorProfile(authorId);

        return ResponseEntity.ok(author);
    }
}


