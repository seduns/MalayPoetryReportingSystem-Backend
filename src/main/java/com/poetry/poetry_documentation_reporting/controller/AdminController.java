package com.poetry.poetry_documentation_reporting.controller;


import com.poetry.poetry_documentation_reporting.model.Admin;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.service.AdminService;
import com.poetry.poetry_documentation_reporting.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAuthor() throws Exception {
        List<Admin> adminList = adminService.getAllAdmin();
        return ResponseEntity.ok(adminList);
    }

}
