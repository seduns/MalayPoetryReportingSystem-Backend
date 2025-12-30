package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PoetryRepository extends JpaRepository<Poetry, Long> {

    @Query("""
        SELECT p.author
        FROM Poetry p
        WHERE p.id = :poetryId
    """)
    Optional<Author> findAuthorByPoetryId(@Param("poetryId") Long poetryId);

}
