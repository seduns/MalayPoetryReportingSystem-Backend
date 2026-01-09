package com.poetry.poetry_documentation_reporting.controller;

import com.poetry.poetry_documentation_reporting.model.Author;
import com.poetry.poetry_documentation_reporting.model.Donation;
import com.poetry.poetry_documentation_reporting.request.DonationRequest;
import com.poetry.poetry_documentation_reporting.request.DonationWithdrawRequest;
import com.poetry.poetry_documentation_reporting.request.WithdrawRequest;
import com.poetry.poetry_documentation_reporting.response.AuthorDonationSummaryResponse;
import com.poetry.poetry_documentation_reporting.response.DonationResponse;
import com.poetry.poetry_documentation_reporting.response.DonationWithdrawResponse;
import com.poetry.poetry_documentation_reporting.response.MonitorDonationResponse;
import com.poetry.poetry_documentation_reporting.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @PatchMapping("/new")
    public ResponseEntity<DonationResponse> newDonation(@RequestBody DonationRequest requeste) throws Exception {

        Donation donation = donationService.makeDonation(requeste.getPoetry(), requeste.getDonationAmount());
        DonationResponse response = new DonationResponse();
        response.setDonationAmount(requeste.getDonationAmount());
        response.setPoetryTitle(donation.getPoetry().getTitle());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/view/author/{authorId}")
    public ResponseEntity<AuthorDonationSummaryResponse> getAuthorDonation(@PathVariable Long authorId) {
        try {
            AuthorDonationSummaryResponse result = donationService.getAuthorDonation(authorId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/withdraw")
    public ResponseEntity<Author> withdrawDonation(@RequestBody WithdrawRequest request) throws Exception {
        Author author = donationService.withdrawDonation(request.getAuthorId(), request.getAmount());
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<MonitorDonationResponse>> getAllDonationList() throws Exception {
        List<MonitorDonationResponse> listOfDonation = donationService.getListOfDonation();
        return new ResponseEntity<>(listOfDonation, HttpStatus.OK);
    }

    @GetMapping("/poetry/{poetryId}")
    public ResponseEntity<Donation> viewDonationByPoetryId(@PathVariable long poetryId) throws Exception {
        Donation donation = donationService.getDonationByPoetryId(poetryId);
        return new ResponseEntity<>(donation, HttpStatus.OK);
    }

}
