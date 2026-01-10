package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoetryRepository extends JpaRepository<Poetry, Long> {

    @Query("""
        SELECT p.author
        FROM Poetry p
        WHERE p.id = :poetryId
    """)
    Optional<Author> findAuthorByPoetryId(@Param("poetryId") Long poetryId);

    List<Poetry> findByAuthorId(Long authorId);

    @Query("SELECT COUNT(p) FROM Poetry p WHERE p.status.name = 'APPROVED'")
    long countApprovedPoetry();

    @Query("SELECT COUNT(p) FROM Poetry p WHERE p.author.user.id = :userId AND p.status.name = 'APPROVED'")
    long countPublishedPoetryByAuthor(@Param("userId") Long userId);


}
