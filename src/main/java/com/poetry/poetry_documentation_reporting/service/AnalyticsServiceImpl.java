package com.poetry.poetry_documentation_reporting.service;

import com.poetry.poetry_documentation_reporting.exception.PoetryAnalyticsNotFoundException;
import com.poetry.poetry_documentation_reporting.exception.PoetryNotFoundException;
import com.poetry.poetry_documentation_reporting.model.Poetry;
import com.poetry.poetry_documentation_reporting.model.PoetryAnalytics;
import com.poetry.poetry_documentation_reporting.model.enumoption.USER_ROLE;
import com.poetry.poetry_documentation_reporting.repository.DonationRepository;
import com.poetry.poetry_documentation_reporting.repository.PoetryAnalyticsRepository;
import com.poetry.poetry_documentation_reporting.repository.PoetryRepository;
import com.poetry.poetry_documentation_reporting.repository.UserRepository;
import com.poetry.poetry_documentation_reporting.response.AdminDashboardStatsResponse;
import com.poetry.poetry_documentation_reporting.response.AuthorDashboardResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    @Autowired
    private PoetryAnalyticsRepository poetryAnalyticsRepository;

    @Autowired
    private PoetryAnalyticsRepository analyticsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private PoetryRepository poetryRepository;

    @Override
    @Transactional
    public void addView(Long poetryId) {

        PoetryAnalytics analytics = analyticsRepository.findByPoetryId(poetryId)
                .orElseThrow(() -> new PoetryAnalyticsNotFoundException("analytics_not_found"));

        analytics.setViewCount(analytics.getViewCount() + 1);
        // No need save() if inside @Transactional
    }

    @Override
    @Transactional
    public void addLike(Long poetryId) {

        PoetryAnalytics analytics = analyticsRepository.findByPoetryId(poetryId)
                .orElseThrow(() -> new PoetryAnalyticsNotFoundException("analytics_not_found"));

        analytics.setLikeCount(analytics.getLikeCount() + 1);
        // No need save() if inside @Transactional
    }

    @Override
    public List<PoetryAnalytics> getAllPoetryAnalytics() throws Exception {
        List<PoetryAnalytics> analyticsList = poetryAnalyticsRepository.findAll();
        return analyticsList;
    }

    @Override
    public PoetryAnalytics getPoetryAnalytics(Long analyticsId) throws Exception {
        return poetryAnalyticsRepository.findById(analyticsId)
                .orElseThrow(() -> new PoetryNotFoundException("poetry_not_found"));
    }

    @Override
    public AdminDashboardStatsResponse adminDashboard() throws Exception {

        return AdminDashboardStatsResponse.builder()
                .totalUser(userRepository.countByRole(USER_ROLE.USER_PUBLIC))
                .totalAuthor(userRepository.countByRole(USER_ROLE.USER_AUTHOR))

                // ✅ Use the new repository methods
                .totalViews(poetryAnalyticsRepository.getTotalSystemViews())
                .totalLikes(poetryAnalyticsRepository.getTotalSystemLikes())

                .totalPoetry(poetryRepository.count())
                .totalApprovedPoetry(poetryRepository.countApprovedPoetry())

                .totalDonationCount(donationRepository.getGrandTotalDonationCount())
                .totalDonationAmount(donationRepository.getGrandTotalDonationValue())
                .build();
    }

    @Override
    public AuthorDashboardResponse authorDashbaord(long authorId) {
        // 2. Fetch Card Data
        long publishedCount = poetryRepository.countPublishedPoetryByAuthor(authorId);
        long totalViews = analyticsRepository.sumViewsByAuthor(authorId);
        long totalLikes = analyticsRepository.sumLikesByAuthor(authorId);
        Double totalDonation = donationRepository.sumDonationByAuthor(authorId);

        // 3. Fetch Chart Data (Top 5)
        var topViewed = analyticsRepository.findTopViewedPoetryByAuthor(authorId, PageRequest.of(0, 5));
        var topDonated = donationRepository.findTopDonationPoetryByAuthor(authorId, PageRequest.of(0, 5));

        // 4. Build Response
        return AuthorDashboardResponse.builder()
                .totalPublishedPoetry(publishedCount)
                .totalViews(totalViews)
                .totalLikes(totalLikes)
                .totalDonation(totalDonation)
                .topViewedPoetry(topViewed)
                .topDonatedPoetry(topDonated)
                .build();
    }
}

