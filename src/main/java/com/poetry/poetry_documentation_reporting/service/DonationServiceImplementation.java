package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.exception.DonationNotFoundException;
import com.poetry.poetry_documentation_reporting.exception.PoetryNotFoundException;
import com.poetry.poetry_documentation_reporting.exception.UserNotFoundException;
import com.poetry.poetry_documentation_reporting.exception.WithdrawDonationOutOfDonation;
import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Donation;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.model.User;
import com.poetry.poetry_documentation_reporting.repository.AuthorRepository;
import com.poetry.poetry_documentation_reporting.repository.DonationRepository;
import com.poetry.poetry_documentation_reporting.repository.PoetryRepository;
import com.poetry.poetry_documentation_reporting.response.AuthorDonationSummaryResponse;
import com.poetry.poetry_documentation_reporting.response.DonationResponse;
import com.poetry.poetry_documentation_reporting.response.MonitorDonationResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImplementation implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private PoetryRepository poetryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public Donation makeDonation(long poetryId, double amount) throws Exception {

        Donation donation = donationRepository.findByPoetryId(poetryId)
                .orElseThrow(() -> new DonationNotFoundException("donation_not_found"));

        Author author = poetryRepository.findAuthorByPoetryId(poetryId)
                .orElseThrow(() -> new UserNotFoundException("user_not_found_by_poetry_id"));

        donation.setDonationValue(donation.getDonationValue() + amount);
        donation.setDonationCount(donation.getDonationCount() + 1);
        author.setCurrentDonationBalance(author.getCurrentDonationBalance() + amount);

        return donation;
    }

    @Override
    public AuthorDonationSummaryResponse getAuthorDonation(long authorId) throws Exception {

        AuthorDonationSummaryResponse donations = donationRepository.getTotalDonationsByAuthor(authorId);

        return donations;
    }

    @Override
    @Transactional
    public Author withdrawDonation(long authorId, double amount) throws Exception {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new UserNotFoundException("user_not_found"));

        if (author.getCurrentDonationBalance() < amount) {
            throw new WithdrawDonationOutOfDonation("withdraw_amount_more_than_balance");
        }

        author.setCurrentDonationBalance(author.getCurrentDonationBalance() - amount);

        return author;
    }

    @Override
    public List<MonitorDonationResponse> getListOfDonation() throws Exception {

        List<Donation> donationList = donationRepository.findAll();
        List<MonitorDonationResponse> monitorDonationResponses = donationList.stream()
                .map(donation -> {
                    Poetry poetry = donation.getPoetry();
                    Author author = poetry.getAuthor();
                    User user = author.getUser();

                    return new MonitorDonationResponse(
                            poetry.getTitle(),
                            user.getFullName(),
                            donation.getDonationCount(),
                            donation.getDonationValue()
                    );

                }).toList();

        return monitorDonationResponses;
    }

    @Override
    public Donation getDonationByPoetryId(long poetryId) throws Exception {
        return donationRepository.findByPoetryId(poetryId)
                .orElseThrow(() -> new PoetryNotFoundException("poetry_not_found"));

    }


}
