package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.PoetryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PoetryStatusRepository extends JpaRepository<PoetryStatus, Long> {
    Optional<PoetryStatus> findByName(String name);
}