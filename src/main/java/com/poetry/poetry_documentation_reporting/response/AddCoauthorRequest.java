package com.poetry.poetry_documentation_reporting.response;

import lombok.Data;

import java.util.List;

@Data
public class AddCoauthorRequest {
    private List<String> coauthors;

}
