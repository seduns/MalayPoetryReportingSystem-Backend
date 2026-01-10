package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Admin;
import com.poetry.poetry_documentation_reporting.model.Author;

import java.util.List;

public interface AdminService {

    public List<Admin> getAllAdmin() throws Exception;

}
