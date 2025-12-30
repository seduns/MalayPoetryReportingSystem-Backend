package com.poetry.poetry_documentation_reporting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coauthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Bridge → Poetry
    @ManyToOne
    @JoinColumn(name = "poetry_id", nullable = false)
    @JsonIgnore
    private Poetry poetry;

    // Bridge → Author
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Optional: extra fields
    // private String roleInPoetry;
    // private int contributionPercentage;
}
