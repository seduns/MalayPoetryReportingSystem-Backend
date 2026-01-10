package com.poetry.poetry_documentation_reporting.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long authorId;
    private String email;
    private String name;
}
