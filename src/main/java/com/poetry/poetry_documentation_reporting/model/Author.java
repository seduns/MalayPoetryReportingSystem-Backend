package com.poetry.poetry_documentation_reporting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.model.enumoption.USER_STATUS;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Public ID, unique, 4-digit
    @Column(unique = true, nullable = false, length = 4)
    private String publicId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Min(value = 0, message = "Current donation value must be positive")
    private double currentDonationBalance;

    private String bio;

    @Enumerated(EnumType.STRING)
    private USER_STATUS status;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Coauthor> coauthoredPoetries = new ArrayList<>();
}
