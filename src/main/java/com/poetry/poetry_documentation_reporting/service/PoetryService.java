package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.request.PoetryRequest;
import com.poetry.poetry_documentation_reporting.response.PoetryStatusResponse;

import java.util.List;

public interface PoetryService {

    public Poetry createPoetry(PoetryRequest request, Long authorId, List<String> coauthorPublicIds) throws Exception;
    public Poetry getPoetry(Long poetryId) throws Exception;
    public List<Poetry> getAllPoetry() throws Exception;
    public Poetry updatePoetry(Long poetryId, PoetryRequest request) throws Exception;
    public PoetryStatusResponse updatePoetryStatus(Long poetryId, String status) throws Exception;

}
