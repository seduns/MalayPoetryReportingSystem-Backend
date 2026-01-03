package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.request.PoetryRequest;
import com.poetry.poetry_documentation_reporting.response.PoetryStatusResponse;

import java.util.List;
import java.util.Optional;

public interface PoetryService {

    public Poetry createPoetry(PoetryRequest request, Long authorId) throws Exception;
    public Poetry getPoetry(Long poetryId) throws Exception;
    public List<Poetry> getAllPoetry() throws Exception;
    public Poetry updatePoetry(Long poetryId, PoetryRequest request) throws Exception;
    public PoetryStatusResponse updatePoetryStatus(Long poetryId, String status) throws Exception;
    public String deletePoetry(Long id) throws Exception;
    public List<Poetry> getPoetryByAuthorId(Long authorId) throws Exception;

}
