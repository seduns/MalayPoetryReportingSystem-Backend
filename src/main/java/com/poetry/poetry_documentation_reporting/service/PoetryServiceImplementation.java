package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.exception.PoetryNotFoundException;
import com.poetry.poetry_documentation_reporting.model.*;
import com.poetry.poetry_documentation_reporting.repository.*;
import com.poetry.poetry_documentation_reporting.request.PoetryRequest;
import com.poetry.poetry_documentation_reporting.response.PoetryStatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PoetryServiceImplementation implements PoetryService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PoetryStatusRepository poetryStatusRepository;

    @Autowired
    private PoetryRepository poetryRepository;

    @Autowired
    private PoetryAnalyticsRepository poetryAnalyticsRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Transactional
    @Override
    public Poetry createPoetry(PoetryRequest request, Long authorId, List<String> coauthorPublicIds) {

        // 1. Create Poetry
        Poetry poetry = new Poetry();
        poetry.setTitle(request.getTitle());
        poetry.setContent(request.getContent());
        poetry.setCategory(request.getCategory());
        poetry.setDescription(request.getDescription());

        // 2. Owner (Author)
        Author owner = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Author not found with ID: " + authorId
                ));
        poetry.setAuthor(owner);

        // 3. Default status (PENDING)
        PoetryStatus status = poetryStatusRepository.findByName("PENDING")
                .orElseThrow(() -> new IllegalArgumentException(
                        "PoetryStatus PENDING not found"
                ));
        poetry.setStatus(status);

        // 4. Coauthors (resolve by publicId)
        if (coauthorPublicIds != null && !coauthorPublicIds.isEmpty()) {

            List<Coauthor> coauthors = new java.util.ArrayList<>();

            for (String publicId : coauthorPublicIds) {

                Author coauthorAuthor = authorRepository.findByPublicId(publicId)
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Coauthor not found with publicId: " + publicId
                        ));

                Coauthor coauthor = new Coauthor();
                coauthor.setPoetry(poetry);   // owning side
                coauthor.setAuthor(coauthorAuthor);

                coauthors.add(coauthor);
            }

            poetry.setCoauthors(coauthors); // inverse side
        }

        // 5. Analytics (auto create)
        PoetryAnalytics analytics = new PoetryAnalytics();
        analytics.setPoetry(poetry);
        analytics.setLikeCount(0);
        analytics.setViewCount(0);
        poetry.setAnalytics(analytics);

        // 6. Donation (auto create)
        Donation donation = new Donation();
        donation.setPoetry(poetry);
        donation.setDonationCount(1);
        donation.setDonationValue(0.0);
        poetry.setDonation(donation);

        // 7. Save (cascade saves everything)
        return poetryRepository.save(poetry);
    }

    @Transactional
    @Override
    public Poetry updatePoetry(Long poetryId, PoetryRequest request) {

        // 1. Find existing poetry
        Poetry poetry = poetryRepository.findById(poetryId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Poetry not found with ID: " + poetryId
                ));

        // 2. Update ONLY editable fields
        poetry.setTitle(request.getTitle());
        poetry.setDescription(request.getDescription());
        poetry.setContent(request.getContent());
        poetry.setCategory(request.getCategory());

        // 3. No need to call save() explicitly
        // Because entity is managed & @Transactional is active
        return poetry;
    }

    @Override
    public PoetryStatusResponse updatePoetryStatus(Long poetryId, String status) throws Exception {

        // 1. Find poetry
        Poetry poetry = poetryRepository.findById(poetryId)
                .orElseThrow(() ->
                        new Exception("Poetry not found with id: " + poetryId));

        // 2. Find new status
        PoetryStatus newStatus = poetryStatusRepository.findByName(status)
                .orElseThrow(() ->
                        new Exception("Poetry status not found: " + status));

        // 3. Store old status (for response)
        String oldStatusName = poetry.getStatus().getName();

        // 4. Update status
        poetry.setStatus(newStatus);

        // 5. Save
        poetryRepository.save(poetry);

        // 6. Return response
        return new PoetryStatusResponse(
                poetry.getId(),
                poetry.getTitle(),
                oldStatusName,
                newStatus.getName()
        );
    }

    @Override
    public Poetry getPoetry(Long poetryId) {
        return poetryRepository.findById(poetryId)
                .orElseThrow(() -> new PoetryNotFoundException("poetry_not_found"));
    }

    @Override
    public List<Poetry> getAllPoetry() throws Exception {
        List<Poetry> poetries = poetryRepository.findAll();
        return poetries;
    }


}
