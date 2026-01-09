package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.model.Admin;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    // ===================== ADMIN =====================
    @PutMapping("/admin/{id}")
    public ResponseEntity<Admin> updateAdminStatus(
            @PathVariable long id,
            @RequestParam String status
    ) throws Exception {

        Admin updatedAdmin = statusService.updateStatusAdmin(id, status);
        return ResponseEntity.ok(updatedAdmin);
    }

    // ===================== AUTHOR =====================
    @PutMapping("/author/{id}")
    public ResponseEntity<Author> updateAuthorStatus(
            @PathVariable long id,
            @RequestParam String status
    ) throws Exception {

        Author updatedAuthor = statusService.updateStatusAuthor(id, status);
        return ResponseEntity.ok(updatedAuthor);
    }
}
