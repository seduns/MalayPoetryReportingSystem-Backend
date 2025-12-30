package com.poetry.poetry_documentation_reporting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poetry.poetry_documentation_reporting.model.enumoption.CATEGORY_POETRY;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poetry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100)
    private String title;

    @NotBlank(message = "Content is required")
    @Lob
    @Column(nullable = false)
    private String content; // max 500 words → service layer validation

    @Size(max = 500)
    private String description; // max 70 words → service layer validation

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CATEGORY_POETRY category;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    // Poetry → Status
    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private PoetryStatus status;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Poetry ↔ Coauthor (many-to-many via Coauthor entity)
    @JsonIgnore
    @OneToMany(mappedBy = "poetry", cascade = CascadeType.ALL)
    private List<Coauthor> coauthors = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "poetry", cascade = CascadeType.ALL)
    private PoetryAnalytics analytics;

    @JsonIgnore
    @OneToOne(mappedBy = "poetry", cascade = CascadeType.ALL)
    private Donation donation;
}
