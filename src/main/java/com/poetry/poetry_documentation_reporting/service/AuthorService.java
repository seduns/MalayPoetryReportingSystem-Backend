package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileRequest;

import java.util.List;

public interface AuthorService {
    public List<Author> getAllAuthor() throws Exception;

    public Author updateAuthorProfile(Long authorId, UpdateProfileRequest request) throws Exception;

    public Author getAuthorProfile(Long authorId) throws Exception;
}
