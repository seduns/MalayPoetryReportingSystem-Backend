package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Donation;
import com.poetry.poetry_documentation_reporting.request.DonationRequest;
import com.poetry.poetry_documentation_reporting.response.AuthorDonationSummaryResponse;
import com.poetry.poetry_documentation_reporting.response.DonationResponse;
import com.poetry.poetry_documentation_reporting.response.MonitorDonationResponse;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface DonationService {

    // get donation

    // make donation
    public Donation makeDonation(long poetryId, double amount) throws Exception;

    // get donation analystics
    public AuthorDonationSummaryResponse getAuthorDonation(long authorId) throws Exception;

    //widthdraw donation for author
    public Author withdrawDonation(long authorId, double amount) throws Exception;

    // get list of donation
    public List<MonitorDonationResponse> getListOfDonation() throws Exception;

    // get donation by poetry id
    public Donation getDonationByPoetryId(long poetryId) throws Exception;

}
