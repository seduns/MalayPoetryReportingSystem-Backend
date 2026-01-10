package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileRequest;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileUser;
import com.poetry.poetry_documentation_reporting.response.AuthorResponse;
import com.poetry.poetry_documentation_reporting.response.UserResponse;
import com.poetry.poetry_documentation_reporting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserResponse> updateProfile(@PathVariable Long userId, @RequestBody UpdateProfileUser request) throws Exception {

        User updatedAuthor = userService.updateUserProfile(userId, request);

        UserResponse response = new UserResponse(
                updatedAuthor.getId(),
                updatedAuthor.getEmail(),
                updatedAuthor.getFullName()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> updateProfile(@PathVariable Long userId) throws Exception {

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

}
