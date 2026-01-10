package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.exception.UserNotFoundException;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.repository.UserRepository;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileRequest;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User updateUserProfile(Long authorId, UpdateProfileUser request) throws Exception {
        User user = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Update basic profile
        user.setEmail(request.getEmail());
        user.setFullName(request.getName());

        // Update password only if provided
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(
                    passwordEncoder.encode(request.getPassword())
            );
        }

        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));
        return user;
    }


}
