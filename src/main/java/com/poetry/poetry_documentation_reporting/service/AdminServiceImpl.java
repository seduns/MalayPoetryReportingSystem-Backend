package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Admin;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public List<Admin> getAllAdmin() throws Exception {
        List<Admin> adminList = adminRepository.findAll();

        return adminList;
    }
}
