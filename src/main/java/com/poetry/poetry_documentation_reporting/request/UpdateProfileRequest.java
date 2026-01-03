package com.poetry.poetry_documentation_reporting.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {
        private String name;
        private String email;
        private String password;
        private String bio;
}
