package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.Coauthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoauthorRepository extends JpaRepository<Coauthor, Long> {
    List<Coauthor> findByPoetry_Id(Long poetryId);
    boolean existsByPoetry_IdAndAuthor_Id(Long poetryId, Long authorId);
}
