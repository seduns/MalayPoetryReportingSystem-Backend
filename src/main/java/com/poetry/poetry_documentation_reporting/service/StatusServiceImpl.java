package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.exception.UserNotFoundException;
import com.poetry.poetry_documentation_reporting.model.Admin;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.enumoption.USER_STATUS;
import com.poetry.poetry_documentation_reporting.repository.AdminRespository;
import com.poetry.poetry_documentation_reporting.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private AdminRespository adminRespository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Admin updateStatusAdmin(long id, String status) throws Exception {

        Admin admin = adminRespository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("admin_not_found_exception"));

        USER_STATUS newStatus = convertToUserStatus(status);

        admin.setStatus(newStatus);
        adminRespository.save(admin);

        return admin;
    }

    @Override
    public Author updateStatusAuthor(long id, String status) throws Exception {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("author_not_found_exception"));

        USER_STATUS newStatus = convertToUserStatus(status);

        author.setStatus(newStatus);
        authorRepository.save(author);

        return author;
    }

    private USER_STATUS convertToUserStatus(String status) {

        if (status == null) {
            throw new IllegalArgumentException("status_cannot_be_null");
        }

        switch (status.trim().toLowerCase()) {
            case "active":
                return USER_STATUS.STATUS_ACTIVE;
            case "inactive":
                return USER_STATUS.STATUS_INACTIVE;
            default:
                throw new IllegalArgumentException("invalid_status_value");
        }
    }
}
