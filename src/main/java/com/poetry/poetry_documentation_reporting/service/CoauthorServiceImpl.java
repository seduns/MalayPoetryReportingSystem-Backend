package com.poetry.poetry_documentation_reporting.service;


import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Coauthor;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.repository.AuthorRepository;
import com.poetry.poetry_documentation_reporting.repository.CoauthorRepository;
import com.poetry.poetry_documentation_reporting.repository.PoetryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoauthorServiceImpl implements CoauthorService {

    @Autowired
    private CoauthorRepository coauthorRepository;

    @Autowired
    private PoetryRepository poetryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public List<Coauthor> addNewCoauthor(List<String> coauthorPublicIds, long poetryId) throws Exception {

        // 1. Find Poetry
        Poetry poetry = poetryRepository.findById(poetryId)
                .orElseThrow(() -> new Exception("Poetry not found with id: " + poetryId));

        List<Coauthor> savedCoauthors = new ArrayList<>();

        // 2. Loop through publicIds
        for (String publicId : coauthorPublicIds) {

            // 3. Find Author by publicId
            Author author = authorRepository.findByPublicId(publicId)
                    .orElseThrow(() ->
                            new Exception("Author not found with publicId: " + publicId));

            // 4. Prevent owner from adding himself as coauthor
            if (author.getId().equals(poetry.getAuthor().getId())) {
                throw new Exception("Owner cannot be added as coauthor");
            }

            // 5. Prevent duplicate coauthor
            boolean exists = coauthorRepository
                    .existsByPoetry_IdAndAuthor_Id(poetryId, author.getId());

            if (exists) {
                throw new Exception(
                        "Author with publicId " + publicId + " is already a coauthor");
            }

            // 6. Create Coauthor entity
            Coauthor coauthor = new Coauthor();
            coauthor.setPoetry(poetry);
            coauthor.setAuthor(author);

            savedCoauthors.add(coauthor);
        }

        // 7. Save all at once
        return coauthorRepository.saveAll(savedCoauthors);
    }

    /**
     * Get all coauthors of a poetry
     */
    @Override
    @Transactional
    public List<Coauthor> getAllCoauthor(long poetryId) throws Exception {

        // Optional: validate poetry exists
        if (!poetryRepository.existsById(poetryId)) {
            throw new Exception("Poetry not found with id: " + poetryId);
        }

        return coauthorRepository.findByPoetry_Id(poetryId);
    }
}
