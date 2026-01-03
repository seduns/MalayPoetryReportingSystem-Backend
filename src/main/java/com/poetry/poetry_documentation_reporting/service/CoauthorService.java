package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Coauthor;
import com.poetry.poetry_documentation_reporting.model.Poetry;

import java.util.List;

public interface CoauthorService {

    // add co author
    public List<Coauthor> addNewCoauthor(List<String> coauthor, long poetryId) throws Exception;

    // get all poetry co author
    public List<Coauthor> getAllCoauthor(long poetryId) throws Exception;

    public Author checkAuthorbyPublicId(String publicId) throws Exception;

}
