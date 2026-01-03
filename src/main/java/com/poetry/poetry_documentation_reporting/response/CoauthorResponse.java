package com.poetry.poetry_documentation_reporting.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoauthorResponse {
    private Long id;
    private String publicId;
    private String fullName;
}