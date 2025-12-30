package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> getAllAuthor() throws Exception;
}
