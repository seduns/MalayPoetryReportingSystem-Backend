package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileRequest;
import com.poetry.poetry_documentation_reporting.request.UpdateProfileUser;

public interface UserService {

    User updateUserProfile(Long authorId, UpdateProfileUser request) throws Exception;

    User getUser(Long userId) throws Exception;
}
