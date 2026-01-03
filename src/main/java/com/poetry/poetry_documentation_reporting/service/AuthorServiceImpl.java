package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.exception.UserNotFoundException;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.repository.AuthorRepository;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Author> getAllAuthor() throws Exception {

        List<Author> authorList = authorRepository.findAll();

        return authorList;
    }

    @Override
    @Transactional
    public Author updateAuthorProfile(Long authorId, UpdateProfileRequest request) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Update basic profile
        author.setBio(request.getBio());
        author.getUser().setEmail(request.getEmail());
        author.getUser().setFullName(request.getName());

        // Update password only if provided
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            author.getUser().setPassword(
                    passwordEncoder.encode(request.getPassword())
            );
        }

        return authorRepository.save(author);
    }

    @Override
    public Author getAuthorProfile(Long authorId) throws Exception {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new UserNotFoundException("user_not_found " + authorId));

    }
}

