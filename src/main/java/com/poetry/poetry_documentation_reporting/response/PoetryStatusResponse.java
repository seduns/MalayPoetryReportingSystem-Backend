package com.poetry.poetry_documentation_reporting.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoetryStatusResponse {

    private Long poetryId;
    private String poetryTitle;
    private String oldStatus;
    private String newStatus;

}