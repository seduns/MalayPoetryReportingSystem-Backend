package com.poetry.poetry_documentation_reporting.request;

import com.poetry.poetry_documentation_reporting.model.enumoption.CATEGORY_POETRY;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoetryRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content; // You can add word count validation in service layer

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    private CATEGORY_POETRY category;

    // List of coauthor IDs (if they already exist) or names depending on your use case
    private List<String> coauthors;

}

