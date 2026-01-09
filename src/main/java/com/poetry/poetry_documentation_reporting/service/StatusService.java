package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Admin;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.response.StatusResponse;

public interface StatusService {

    Admin updateStatusAdmin(long id, String status) throws Exception;
    Author updateStatusAuthor(long id, String status) throws Exception;

}
