package com.poetry.poetry_documentation_reporting.repository;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Donation;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import com.poetry.poetry_documentation_reporting.response.AuthorDonationSummaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {

//    @Query("""
//        select COALESCE(sum(d.currentDonationValue), 0)
//        from Donation d
//        where d.poetry.author.id = :authorId
//    """)
//    double getTotalDonationByAuthor(@Param("authorId") Long authorId);
//    List<Donation> findByPoetryAuthorId(Long authorId);

    @Query("""
    SELECT new com.poetry.poetry_documentation_reporting.response.AuthorDonationSummaryResponse(
        a.id,
        SUM(d.donationValue),
        a.currentDonationBalance
    )
    FROM Donation d
    JOIN d.poetry p
    JOIN p.author a
    WHERE a.id = :authorId
    GROUP BY a.id, a.currentDonationBalance
    """)
    AuthorDonationSummaryResponse getTotalDonationsByAuthor(@Param("authorId") Long authorId);

    Optional<Donation> findByPoetryId(long poetryId);



}
