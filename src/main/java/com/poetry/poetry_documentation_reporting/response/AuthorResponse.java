package com.poetry.poetry_documentation_reporting.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorResponse {
    private Long authorId;
    private String bio;
    private String email;
    private String name;
}
