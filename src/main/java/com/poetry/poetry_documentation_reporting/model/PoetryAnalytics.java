package com.poetry.poetry_documentation_reporting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoetryAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Like count must be positive")
    private int likeCount = 0;

    @Min(value = 0, message = "View count must be positive")
    private int viewCount = 0;

    // OneToOne relationship with Poetry
    @OneToOne
    @JoinColumn(name = "poetry_id", nullable = false, unique = true)
    private Poetry poetry;
}
