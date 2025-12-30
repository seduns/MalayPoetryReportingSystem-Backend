package com.poetry.poetry_documentation_reporting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "Donation count must be at least 0")
    private int donationCount;

    @Min(value = 0, message = "Donation value must be positive")
    private double donationValue;


    // Relationship ke Poetry
    @OneToOne
    @JoinColumn(name = "poetry_id", nullable = false, unique = true)
    private Poetry poetry;
}
